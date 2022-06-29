package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.*;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserAlreadyExists;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserNotFound;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public UserService() {
        //Empty constructor
    }

    @Transactional
    public ArrayList<User> getUsers(){
        return (ArrayList<User>) this.userRepository.findAll();
    }

    @Transactional
    public User findUser(Long id){
        return  userRepository.findById(id).orElseThrow(UserNotFound::new);
    }

    @Transactional
    public User createUser(UserCreateDTO usuario) {
        if(userAlreadyExists(usuario)){
            throw new UserAlreadyExists();
        }
        if(this.isValidUser(usuario)){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            User user = new User(usuario.getName(), usuario.getSurname(), usuario.getEmail(), usuario.getAddress(),
                                encodedPassword, usuario.getCvu(), usuario.getWallet());
            return this.userRepository.save(user);
        }
        throw new UserError("One or more fields are incorrect");
    }

    @Transactional
    public boolean isValidUser(UserCreateDTO user) {
        return  validate(user.getName(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getSurname(), "^[a-zA-Z ]{3,30}$") &&
                validate(user.getEmail(), "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+(?:\\.[a-zA-Z0-9-]+)*$") &&
                validate(user.getAddress(), "^[a-zA-Z0-9 ]{10,30}$") &&
                validate(user.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{6,}$") &&
                validate(user.getCvu(), "^[a-zA-Z0-9]{22}$") &&
                validate(user.getWallet(), "^[a-zA-Z0-9]{8}$");
    }

    @Transactional
    public boolean userAlreadyExists(UserCreateDTO user){
       return userRepository.existWallet(user.getWallet());
    }

    @Transactional
    public boolean validate(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    @Transactional
    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        User user = this.findUser(id);
        this.userRepository.deleteById(user.getId());
    }

    @Transactional
    public UserTradedVolumeDTO getTradedVolume(DatesDTO dates, Long id, List<Transaction> transactions) {

        User userFound = this.findUser(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateFrom = LocalDateTime.parse(dates.getDateFrom(), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(dates.getDateTo(), formatter);

        ArrayList<Transaction> processedTransactions = transactions.stream().filter(t -> t.getStatus().equals("Procesada") &&
                                                                    this.isBetweenDates(t.getDateAndTime(), dateFrom, dateTo) &&
                                                                    (t.getUser().getId().equals(id) || t.getSecondaryUser().getId().equals(id)))
                                                                    .collect(Collectors.toCollection(ArrayList::new));
        Float volumeInUSD = this.volumeInUSD(processedTransactions);
        Float volumeInARS = this.volumeInARS(processedTransactions);
        ArrayList<CryptoActiveDTO> cryptoActives = this.getCryptoActivesFromTransactions(processedTransactions);

        return new UserTradedVolumeDTO(userFound.getFullName(), LocalDateTime.now().toString(), volumeInUSD, volumeInARS, cryptoActives);
    }


    public boolean isBetweenDates(LocalDateTime dateAndTime, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return dateAndTime.isAfter(dateFrom) && dateAndTime.isBefore(dateTo);
    }


    public float volumeInUSD(ArrayList<Transaction> transactions) {
        float sum = 0;
        for(int i = 0; i < transactions.size(); i++) {
            sum = sum + transactions.get(i).getPriceOfCrypto();
        }
        return sum;
    }


    public float volumeInARS(ArrayList<Transaction> transactions) {
        float sum = 0;
        for(int i = 0; i < transactions.size(); i++) {
            sum = sum + transactions.get(i).getFinalPriceInARS();
        }
        return sum;
    }


    public ArrayList<CryptoActiveDTO> getCryptoActivesFromTransactions(ArrayList<Transaction> transactions) {
       // ArrayList<CryptoCurrency> cryptoCurrencies = transactions.stream().map(t -> t.getCrypto()).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<CryptoActiveDTO> cryptoActiveDTOS = new ArrayList<>();
        for(int i = 0; i < transactions.size(); i++){
            Transaction transaction = transactions.get(i);
            CryptoActiveDTO dto = new CryptoActiveDTO(transaction.getCrypto(), transaction.getAmountOfCrypto(), transaction.getPriceOfCrypto(),
                                                    transaction.getFinalPriceInARS());
            cryptoActiveDTOS.add(dto);
        }
        return cryptoActiveDTOS;
    }
/*
    @Transactional
    public TokenDTO login(AuthUserDTO authUserDTO) {
        Optional<User> user = userRepository.findUserByName(authUserDTO.getName());
        if(!user.isPresent())
            throw new UserNotFound();
        if(passwordEncoder.matches(authUserDTO.getPassword(), user.get().getPassword()))
            return new TokenDTO(jwtProvider.createToken(user.get()));
        return null;
    }

    @Transactional
    public TokenDTO validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!userRepository.findUserByName(username).isPresent())
            return null;
        return new TokenDTO(token);
    }*/
}