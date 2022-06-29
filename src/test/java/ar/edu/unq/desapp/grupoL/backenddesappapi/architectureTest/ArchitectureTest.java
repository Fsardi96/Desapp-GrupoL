package ar.edu.unq.desapp.grupoL.backenddesappapi.architectureTest;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses (packages = "ar.edu.unq.desapp.grupoL.backenddesappapi")
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule servicesShouldOnlyBeAccessedByControllersAndAspects = classes()
            .that().resideInAPackage ("..service..")
            .should().onlyBeAccessed().byAnyPackage("..webservice..", "..service..","..security..");

    @ArchTest
    public static final ArchRule controllersShouldNotBeAccessedByAnyone = classes()
            .that().resideInAPackage("..webservice..")
            .should().onlyBeAccessed().byAnyPackage("..webservice..");

    @ArchTest
    public static final ArchRule repositoryClassesShouldOnlyBeAccessedByServices = classes()
            .that().resideInAPackage("..repositories..")
            .should().onlyBeAccessed().byAnyPackage("..service..", "..repositories..");

    @ArchTest
    public static final ArchRule classesWithNameServiceAtTheEndShouldBeOnlyInServicePackage = classes()
            .that().haveSimpleNameEndingWith("Service")
            .should().resideInAPackage("..service..");

    @ArchTest
    public static final ArchRule classesWithNameControllerAtTheEndShouldBeOnlyInControllerPackage = classes()
            .that().haveSimpleNameEndingWith("Controller")
            .should().resideInAPackage("..webservice..");

    @ArchTest
    public static final ArchRule classesWithNameRepositoryAtTheEndShouldBeOnlyInRepositoryPackage = classes()
            .that().haveSimpleNameEndingWith("Repository")
            .should().resideInAPackage("..repositories..");

    @ArchTest
    public static final ArchRule classesWithNameDTOAtTheEndShouldBeOnlyInDtosPackage = classes()
            .that().haveSimpleNameEndingWith("DTO")
            .should().resideInAPackage("..model.Dtos..");

    @ArchTest
    public static final ArchRule repositoryClassesShouldBeInterfaces = classes()
            .that().haveSimpleNameEndingWith("Repository")
            .should().beInterfaces();

    @ArchTest
    public static final ArchRule onlyClassesInServiceShouldHaveTransactionalAnnotation = classes()
            .that().areAnnotatedWith(Transactional.class)
            .should().resideInAPackage("..service..");

    @ArchTest
    public static final ArchRule onlyClassesInControllerShouldHaveRestControllerAnnotation = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..webservice..");

    @ArchTest
    public static final ArchRule onlyClassesInRepositoriesShouldHaveRepositoryAnnotation = classes()
            .that().areAnnotatedWith(Repository.class)
            .should().resideInAPackage("..repositories..");

    @ArchTest
    public static final ArchRule onlyClassesInModelShouldHaveEntityAnnotation = classes()
            .that().areAnnotatedWith(Entity.class)
            .should().resideInAPackage("..model..");

}
