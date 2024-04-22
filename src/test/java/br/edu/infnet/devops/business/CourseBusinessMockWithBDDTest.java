package br.edu.infnet.devops.business;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import br.edu.infnet.devops.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


class CourseBusinessMockWithBDDTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup() {

        // Given / Arrange
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);

        courses = Arrays.asList(
                "Administração de BDs noSQL com MongoDB",
                "Arquitetura de Redes de Computadores",
                "Desenvolvimento com Serviços WCF e Microsoft Azure",
                "Melhores Práticas em DevOps",
                "Segurança no Processo e Desenvolvimento de Software",
                "Projeto de Bloco: Desenvolvimento Android",
                "Projeto de Bloco: Desenvolvimento Java",
                "Projeto de Bloco: Engenharia de Dados: Big Data",
                "Projeto de Bloco: Engenharia de Softwares Escaláveis",
                "Projeto de Bloco: Engenharia Disciplinada de Softwares"

        );
    }

    @Test
    void testCoursesRelatedToDevOps_When_UsingAMock() {

        // Given / Arrange
        given(mockService.retrieveCourses("Carolina"))
                .willReturn(courses);

        // When / Act
        var filteredCourses =
                business.retriveCoursesRelatedToDevOps("Carolina");

        // Then / Assert
        assertThat(filteredCourses.size(), is(1));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Delete Courses not Related to DevOps Using Mockito should call Method deleteCourse")
    @Test
    void testDeleteCoursesNotRelatedToDevOps_UsingMockitoVerify_Should_CallMethod_deleteCourse() {

        // Given / Arrange
        given(mockService.retrieveCourses("Carolina"))
                .willReturn(courses);

        // When / Act
        business.deleteCoursesNotRelatedToDevOps("Carolina");

        // Then / Assert
        // verify(mockService)
        //    .deleteCourse("Arquitetura de Redes de Computadores");
        // verify(mockService, times(1))
        //    .deleteCourse("Arquitetura de Redes de Computadores");
        // verify(mockService, atLeast(1))
        verify(mockService, atLeastOnce())
                .deleteCourse("Arquitetura de Redes de Computadores");
        verify(mockService)
                .deleteCourse("Projeto de Bloco: Desenvolvimento Android");
        verify(mockService, never())
                .deleteCourse("Javascript");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Delete Courses not Related to DevOps Using Mockito sould call Method deleteCourse V2")
    @Test
    void testDeleteCoursesNotRelatedToDevOps_UsingMockitoVerify_Should_CallMethod_deleteCourseV2() {

        // Given / Arrange
        given(mockService.retrieveCourses("Carolina"))
                .willReturn(courses);

        String arquiteturaCourse = "Arquitetura de Redes de Computadores";
        String androidCourse = "Projeto de Bloco: Desenvolvimento Android";
        String javaCourse = "Javascript";

        // When / Act
        business.deleteCoursesNotRelatedToDevOps("Carolina");

        then(mockService).should().deleteCourse(arquiteturaCourse);
        then(mockService).should().deleteCourse(androidCourse);
        then(mockService).should(never()).deleteCourse(javaCourse);
    }

    @DisplayName("Delete Courses not Related to DevOps Capturing Arguments sould call Method deleteCourse V2")
    @Test
    void testDeleteCoursesNotRelatedToDevOps_CapturingArguments_Should_CallMethod_deleteCourseV2() {


        given(mockService.retrieveCourses("Carolina"))
                .willReturn(courses);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        //String arquiteturaCourse = "Arquitetura de Redes de Computadores";

        // When / Act
        business.deleteCoursesNotRelatedToDevOps("Carolina");

        // then(mockService).should().deleteCourse(argumentCaptor.capture());
        // assertThat(argumentCaptor.getValue(), is("Arquitetura de Redes de Computadores"));

        then(mockService).should(times(9)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(), is(9));
    }
}