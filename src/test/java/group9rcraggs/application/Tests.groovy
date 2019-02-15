package group9rcraggs.application

import static org.junit.Assert.*

import group9rcraggs.application.repository.PageRepository
import group9rcraggs.application.repository.UserRepository
import group9rcraggs.application.repository.WebsiteRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import spock.lang.Specification
import org.hamcrest.Matchers
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat
import static org.hamcrest.MatcherAssert.*;


@SpringBootTest(classes=[Application.class])
public class Tests extends Specification {

	@Autowired
	UserRepository userRepo;
	
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	PageRepository organizerRepo;
	

	
	def "user repo empty" () {
		given:
			userRepo.deleteAll()
		expect:
			assertThat(userRepo.findAll(), Matchers.hasSize(0));
	}
	
	
}
