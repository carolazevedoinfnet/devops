package br.edu.infnet.devops.service.stubs;


import br.edu.infnet.devops.service.CourseService;

import java.util.Arrays;
import java.util.List;


public class CourseServiceStub implements CourseService {

    public List<String> retrieveCourses(String student) {
        return Arrays.asList(
                "Administração de BDs noSQL com MongoDB",
                "Arquitetura de Redes de Computadores",
                "Desenvolvimento com Serviços WCF e Microsoft Azure",
                "Melhores Práticas em DevOps",
                "Segurança no Processo e Desenvolvimento de Software"
        );
    }

    @Override
    public List<String> doSomething(String student) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCourse(String course) {
        // TODO Auto-generated method stub
    }

}