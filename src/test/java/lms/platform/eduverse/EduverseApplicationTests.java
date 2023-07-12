package lms.platform.eduverse;

import lms.platform.eduverse.models.Course;
import lms.platform.eduverse.services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApp {

	@Autowired
	private CourseService courseService;

	@Test
	void contextLoads() {
	}

}
