package group9rcraggs.application

import static org.junit.Assert.*

import group9rcraggs.application.repository.PageRepository;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;
import group9rcraggs.application.*;
import group9rcraggs.application.domain.*;


import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat
import static org.hamcrest.MatcherAssert.*;
import spock.lang.Specification;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import spock.lang.*;
import group9rcraggs.application.controller.*;
import org.junit.Test




@ContextConfiguration
@SpringBootTest(classes=[Application.class, IndexController.class, PageController.class])
public class Tests extends Specification {

	@Autowired
	UserRepository userRepo;
	
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	PageRepository organizerRepo;
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private ResultActions result;
	
	
	
	//=======================================================
	//==========================USER=========================
	//=======================================================
	
	def "user repo empty" () {
		given: "empty user repository"
			userRepo.deleteAll()
		expect: "empty user repository"
			assertThat(userRepo.findAll(), Matchers.hasSize(0));
	}
	
	
	def "user store" () {
		given:
			userRepo.deleteAll()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("asd")
			userRepo.save(u)
		expect:
			assertThat(userRepo.findAll(), Matchers.hasSize(1));
	}
	
	def "user fail password is null" () {
		given:
			userRepo.deleteAll()
			User u = new User();
			u.setLogin("asd")
			u.setPassword(null)
		when:
			userRepo.save(u)
		then:
			thrown(DataIntegrityViolationException)
	}
	
	def "user fail login not unique" () {
		given:
			userRepo.deleteAll()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("p1")
			userRepo.save(u)
			u = new User();
			u.setLogin("asd")
			u.setPassword("p435435")
		when:
			userRepo.save(u)
		then:
			thrown(DataIntegrityViolationException)
	}
	
	//=======================================================
	//=======================================================
	//=======================================================
	
	
	//=======================================================
	//=========================WEBSITE=======================
	//=======================================================
	
	
	def "Website store" () {
		given:
			userRepo.deleteAll()
			websiteRepo.deleteAll()
			Website o = new Website()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("asd")
			o.setOwner(u)
			websiteRepo.save(o)
		expect:
			assertThat(websiteRepo.findAll(), Matchers.hasSize(1));
	}
	
//	def "Website fail no user" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//		when:
//			websiteRepo.save(o)
//		then:
//			thrown(DataIntegrityViolationException)
//	}
//	
//	
//	def "Website user cascade user" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			o.setOwner(u)
//		when:
//			websiteRepo.save(o)
//		then:
//			assertThat(userRepo.findByLogin("asd"), equalTo(u))
//	}
//	
//	
//	def "Website user cascade user deletion" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			o.setOwner(u)
//			websiteRepo.save(o)
//		when:
//			userRepo.delete(u)
//		then:
//			assertThat(websiteRepo.findAll(), Matchers.hasSize(0));
//	}
	
	
	def "Website owner stays for Website deletion" () {
		given:
			userRepo.deleteAll()
			websiteRepo.deleteAll()
			Website o = new Website()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("asd")
			o.setOwner(u)
			websiteRepo.save(o)
		when:
			websiteRepo.delete(o)
		then:
			assertThat(userRepo.findAll(), Matchers.hasSize(1));
	}
	
	
//	def "two users can track same Website" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			u.getWebsites().add(o);
//			o.setOwner(u)
//			userRepo.save(u)
//			u = new User();
//			u.setLogin("2134")
//			u.setPassword("3243")
//			u.getWebsites().add(o);
//			o.setOwner(u)
//		when:
//			userRepo.save(u)
//		then:
//			noExceptionThrown()
//	}
	
	
	//=======================================================
	//=======================================================
	//=======================================================
	
	
	//=======================================================
	//=========================PAGES=========================
	//=======================================================
	
//	def "Website owner cascade persist page" () {
//		given:
//			userRepo.deleteAll()
//			organizerRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			o.setOwner(u)
//			Page t = new Page()
//			t.setName("amsdl")
//		when:
//			websiteRepo.save(o)
//		then:
//			assertThat(organizerRepo.findByName("amsdl"), equalTo(t))
//	}
//	
//	def "Website owner cascade delete pages" () {
//		given:
//			userRepo.deleteAll()
//			organizerRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			o.setOwner(u)
//			Page t = new Page()
//			t.setName("amsdl")
//			websiteRepo.save(o)
//		when:
//			websiteRepo.delete(o);
//		then:
//			assertThat(websiteRepo.findAll(), Matchers.empty());
//	}
//	
//	def "Website owner deleting Page" () {
//		given:
//			userRepo.deleteAll()
//			organizerRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			o.setOwner(u)
//			Page t = new Page()
//			t.setName("amsdl")
//			websiteRepo.save(o)
//		when:
//			organizerRepo.delete(t);
//		then:
//			assertThat(organizerRepo.findAll(), Matchers.empty());
//		and:
//			assertThat(websiteRepo.findByOwner(u), Matchers.hasSize(1));
//	}
	

	//=======================================================
	//=======================================================
	//=======================================================
	
	
	
	
	//=======================================================
	//=====================NAVIGATION========================
	//=======================================================
	
	
	
	def "Login register view from Index page button"() {
		given: "the context of the controller is setup"
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
		when: "I perform a HTTP get /login_register"
			result = this.mockMvc.perform(get("/login_register"))
		then: "the status of the HTTP response should be Ok (200)"
			result.andExpect(status().is(200))
		and:  "I should see the view CreateTodo"
			result.andExpect(view().name("log_reg"))
	}
	
	
	
	
	
	//=======================================================
	//=======================================================
	//=======================================================
	
	
}
