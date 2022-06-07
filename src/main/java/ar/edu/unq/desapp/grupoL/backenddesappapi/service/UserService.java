package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.CryptoActiveDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.DatesDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserTradedVolumeDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserAlreadyExists;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserNotFound;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

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
            User user = new User(usuario.getName(), usuario.getSurname(), usuario.getEmail(), usuario.getAddress(),
                                 usuario.getPassword(), usuario.getCvu(), usuario.getWallet());
            return this.userRepository.save(user);
        }
        throw new UserError("One or more fields are incorrect");
    }

    public boolean isValidUser(UserCreateDTO user) {
        return  validate(user.getName(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getSurname(), "^[a-zA-Z ]{3,30}$") &&
                validate(user.getEmail(), "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+(?:\\.[a-zA-Z0-9-]+)*$") &&
                validate(user.getAddress(), "^[a-zA-Z0-9 ]{10,30}$") &&
                validate(user.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{6,}$") &&
                validate(user.getCvu(), "^[a-zA-Z0-9]{22}$") &&
                validate(user.getWallet(), "^[a-zA-Z0-9]{8}$");
    }

    public boolean userAlreadyExists(UserCreateDTO user){
       return userRepository.existWallet(user.getWallet());
    }

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

    public UserTradedVolumeDTO getTradedVolume(DatesDTO dates, Long id, List<Transaction> transactions) {

        User userFound = this.findUser(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateFrom = LocalDateTime.parse(dates.getDate1(), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(dates.getDate2(), formatter);

        ArrayList<Transaction> processedTransactions = transactions.stream().filter(t -> t.getStatus().equals("Procesada") &&
                                                                    (t.getUser().getId().equals(id) || t.getSecondaryUser().getId().equals(id)))
                                                                    .collect(Collectors.toCollection(ArrayList::new));

        Float volumeInUSD = this.volumeInUSD(processedTransactions);
        Float volumeInARS = this.volumeInARS(processedTransactions);
        ArrayList<CryptoActiveDTO> cryptoActives = this.getCryptoActivesFromTransactions(processedTransactions);

        return new UserTradedVolumeDTO(userFound.getFullName(), LocalDateTime.now().toString(), volumeInUSD, volumeInARS, cryptoActives);
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
        ArrayList<CryptoCurrency> cryptoCurrencies = transactions.stream().map(t -> t.getCrypto()).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<CryptoActiveDTO> cryptoActiveDTOS = new ArrayList<>();
        for(int i = 0; i < cryptoCurrencies.size(); i++){
            CryptoCurrency crypto = cryptoCurrencies.get(i);
            CryptoActiveDTO dto = new CryptoActiveDTO(crypto.getSymbol(), crypto.getAmount(), crypto.getPrice(), crypto.getPriceInARS());
            cryptoActiveDTOS.add(dto);
        }
        return cryptoActiveDTOS;
    }
}
