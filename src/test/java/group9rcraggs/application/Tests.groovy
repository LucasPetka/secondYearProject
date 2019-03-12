package group9rcraggs.application

import static org.junit.Assert.*

import group9rcraggs.application.*;
import group9rcraggs.application.domain.*;
import group9rcraggs.application.repository.NotificationsRepository
import group9rcraggs.application.repository.PageRepository;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;


import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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

import java.security.SecureRandom

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
	PageRepository pageRepo;
	
	@Autowired
	NotificationsRepository alertRepo;
	
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private ResultActions result;
	
	
	
//	//* A User can name a page *// -- START
//	
//	def "name a page"() {
//		
//		given: "a new page"
//			Page p = new Page();
//		when: "A name is set"
//			p.setName("test");
//		then: "The page has this name"
//			p.getName().equals("test"); 
//	}
//	
//	def "Page name is null" () {
//		
//		given:"A new page with no name"
//		Page p = new Page();
//			p.setName(null);
//	    when: "Page is added to database"
//			pageRepo.save(p)
//		then:
//			thrown(DataIntegrityViolationException)
//	}
//	
//	
//	def "Page is added to website" () {
//		given: "User, website and page created with page name"
//			userRepo.deleteAll()
//			pageRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//			o.setOwner(u)
//			websiteRepo.save(o)
//			Page t = new Page()
//			t.setName("123")
//			t.setOwner(o)
//		when: "Page is added to database"
//			pageRepo.save(t)
//		then: "Page is found by its name"
//			assertFalse(pageRepo.findByName("123").isEmpty())
//	}
//	
//	def "Page without name is added to database" () {
//		given: "User, website and page created without page name"
//			userRepo.deleteAll()
//			pageRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//			o.setOwner(u)
//			websiteRepo.save(o)
//			Page t = new Page()
//			t.setName(null)
//			t.setOwner(o)
//		when:
//			pageRepo.save(t)
//		then:
//			thrown(DataIntegrityViolationException)
//}
//	
//
//		//* A User can name a page *// -- END
//
//
//		//* A User can add a url to a page *// -- START
//
//		def "add url to a page"() {
//	
//			given: "a new page"
//		Page p = new Page();
//			when: "A url is set"
//		p.setName("test");
//			then: "The page has this url"
//		p.getName().equals("test");
//		}
//
//		def "Page url is null" () {
//	
//			given:"A new page with no url"
//				Page p = new Page();
//				p.setName(null);
//			when: "Page is added to database"
//				pageRepo.save(p)
//			then:
//				thrown(DataIntegrityViolationException)
//		}
//
//
//
//		def "Page without url is added to database" () {
//			given: "User, website and page created without page url"
//				userRepo.deleteAll()
//				pageRepo.deleteAll()
//				websiteRepo.deleteAll()
//				Website o = new Website()
//				User u = new User();
//				u.setLogin("asd")
//				u.setPassword("asd")
//				userRepo.save(u)
//				o.setOwner(u)
//				websiteRepo.save(o)
//				Page t = new Page()
//				t.setUrl(null)
//				t.setOwner(o)
//			when:
//				pageRepo.save(t)
//			then:
//				thrown(DataIntegrityViolationException)
//		}
//
//
//		//* A User can add a url to a page *// -- END
//
//
//	//=======================================================
//	//===============A User can sign up======================
//	//=======================================================
//	
//	def "user repo empty" () {
//		given: "empty user repository"
//			userRepo.deleteAll()
//		expect: "empty user repository"
//			assertThat(userRepo.findAll(), Matchers.hasSize(0));
//			
//	}
//	
//	def "user store" () {
//		given:
//			userRepo.deleteAll()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//		expect:
//			assertThat(userRepo.findAll(), Matchers.hasSize(1));
//	}
//	
//	def "user fail password is null" () {
//		given:
//			userRepo.deleteAll()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword(null)
//		when:
//			userRepo.save(u)
//		then:
//			thrown(DataIntegrityViolationException)
//	}
//	
//	def "user fail login not unique" () {
//		given:
//			userRepo.deleteAll()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("p1")
//			userRepo.save(u)
//			u = new User();
//			u.setLogin("asd")
//			u.setPassword("p435435")
//		when:
//			userRepo.save(u)
//		then:
//			thrown(DataIntegrityViolationException)
//	}
//	
//	def "Login register view from Index page button"() {
//		given: "the context of the controller is setup"
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//		when: "I perform a HTTP get /login_register"
//			result = this.mockMvc.perform(get("/login_register"))
//		then: "the status of the HTTP response should be Ok (200)"
//			result.andExpect(status().is(200))
//		and:  "I should see the view log_reg"
//			result.andExpect(view().name("log_reg"))
//	}
//	
//	
//
//	//=======================================================
//	//========================END===========================
//	//=======================================================
//	
//	
//	//=======================================================
//	//===============A User can log in=======================
//	//=======================================================
//	
//	def "View NoPermission displayed when login rejected"() {
//		given: "the context of the controller is setup"
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//		when: "I perform an HTTP GET /access-denied"
//			result = this.mockMvc.perform(get("/access-denied"))
//		then: "the status of the HTTP response should be Ok (200)"
//			result.andExpect(status().is(200))
//		and:  "I should see the view NoPermission"
//			result.andExpect(view().name("NoPermission"))
//	}
//	
//	def "Error thrown when visiting error-login"() {
//		given: "the context of the controller is setup"
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//		when: "I perform an HTTP GET /access-denied"
//			result = this.mockMvc.perform(get("/error-login"))
//		then: "the status of the HTTP response should be Ok (200)"
//			result.andExpect(status().is(200))
//		and:  "I should see the view log_reg"
//			result.andExpect(view().name("log_reg"))
//	}
//	
//	def "Successful login shows webList view"() {
//		given: "the context of the controller is setup"
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//		when: "I perform an HTTP GET /success-login"
//			result = this.mockMvc.perform(get("/success-login"))
//		then: "the status of the HTTP response should be Ok (302)"
//			result.andExpect(status().is(302))
//		and:  "I should see the view WebList"
//			result.andExpect(redirectedUrl("/websiteList"))
//	}
//	
//	
//	
//	//=======================================================
//	//========================END============================
//	//=======================================================
//	
//	//=======================================================
//	//===============A User can change password==============
//	//=======================================================
//	
//	
//	def "User changes password" () {
//		given: "A user with password"
//			userRepo.deleteAll()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//		when: "password is changed"
//			u.setPassword("asd1")
//		then: "User has new password"
//			u.getPassword().equals("asd1");
//	}
//	
//	def "User changes password to null" () {
//		given: "A user with password"
//			userRepo.deleteAll()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//		when: "password is changed to null"
//			u.setPassword(null)
//			userRepo.save(u);
//		then: "Error is thrown"
//			thrown(DataIntegrityViolationException)
//	}
//	
//	def "User tries to access post /changed through url"() {
//		given: "the context of the controller is setup"
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//		when: "I perform an POST GET /changed"
//			result = this.mockMvc.perform(get("/changed"))
//		then: "the status of the HTTP response should be Bad (405)"
//			result.andExpect(status().is(405))
//	}
//	
//	
//
//	
//	//=======================================================
//	//========================END============================
//	//=======================================================
//	
//	
//	//=======================================================
//	//======A user can see when a page was last updated======
//	//=======================================================
//	
//	
//	
//	def "Page is with last updated is added to datbase" () {
//		given: "User, website and page created"
//			userRepo.deleteAll()
//			pageRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//			o.setOwner(u)
//			websiteRepo.save(o)
//			Page t = new Page()
//			t.setLastUpdated("1");
//			t.setOwner(o)
//		when:
//			pageRepo.save(t)
//		then:
//			thrown(DataIntegrityViolationException)
//	}
//	
//	def "Get last updated" () {
//		given: "User, website and page created with last updated '1'"
//			userRepo.deleteAll()
//			pageRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//			o.setOwner(u)
//			websiteRepo.save(o)
//			Page t = new Page()
//			t.setLastUpdated("1");
//			t.setOwner(o)
//		expect:
//			assertTrue(t.getLastUpdated().equals("1"));
//	}
//
//	
//	
//	
//	//=======================================================
//	//========================END============================
//	//=======================================================
//	
//	
//	
//	//=======================================================
//	//=========================EXTRAS=======================
//	//=======================================================
//	
//	def "Website store" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			u.addWebsite(o)
//			o.setOwner(u)
//			userRepo.save(u)
//			websiteRepo.save(o)
//		expect:
//			assertThat(websiteRepo.findAll(), Matchers.hasSize(1));
//	}
//	
//	def "Website fail no user" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//		when:
//			websiteRepo.save(o)
//		then:
//			thrown(Exception)
//	}
//	
//	
//	def "Website user cascade user deletion" () {
//			given:
//				userRepo.deleteAll()
//				websiteRepo.deleteAll()
//				Website o = new Website()
//				User u = new User();
//				u.setLogin("asd")
//				u.setPassword("asd")
//				u.addWebsite(o)
//				o.setOwner(u)
//				userRepo.save(u)
//				websiteRepo.save(o)
//			when:
//				userRepo.delete(u)
//			then:
//				assertThat(websiteRepo.findAll(), Matchers.hasSize(0));
//		}
//	
//	
//	def "Website owner stays for Website deletion" () {
//		given:
//			userRepo.deleteAll()
//			websiteRepo.deleteAll()
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			u.addWebsite(o)
//			o.setOwner(u)
//			userRepo.save(u)
//			websiteRepo.save(o)
//		when:
//			websiteRepo.delete(o)
//		then:
//			assertThat(userRepo.findAll(), Matchers.hasSize(1));
//	}
//
//	
//	def "Pagelist is null" () {
//		given:
//			pageRepo.deleteAll()
//			Page t = new Page()
//		when:
//			pageRepo.save(t)
//		then:
//			thrown(DataIntegrityViolationException)
//	}
//	//=======================================================
//	//=========A user can reset their password===============
//	//=======================================================
//	
//	def "User requests password reset page"() {
//		given: "the context of the controller is setup"
//		
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//		when: "I perform a GET /password_reset"
//			result = this.mockMvc.perform(get("/password_reset"))
//		then: "the status of the HTTP response should be Ok (200)"
//			result.andExpect(status().is(200))
//	}
//	
//	def "User requests to reset password"() {
//		given: "the context of the controller is setup"
//			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
//			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
//			User u = new User();
//			u.setLogin("netnag.t@gmail.com")
//			u.setPassword("123123123Aa")
//			userRepo.save(u)
//		when: "I perform a POST /password_reset with email"
//			result = this.mockMvc.perform(post("/password_reset").param("email", "netnag.t@gmail.com"))
//		then: "the status of the HTTP response should be Ok (200)"
//			result.andExpect(status().is(200))
//	}
//	//=======================================================
//	//=========A user can reset their password=====END=======
//	//=======================================================
//	
//	
//	
//	//=======================================================
//	//===========================Notifications===============
//	//=======================================================
//	def "Notifications gets added"() {
//		given: "the context of the controller is setup"
//			userRepo.deleteAll()
//			pageRepo.deleteAll()
//			websiteRepo.deleteAll()
//			alertRepo.deleteAll()
//			
//			Website o = new Website()
//			User u = new User();
//			u.setLogin("asd")
//			u.setPassword("asd")
//			userRepo.save(u)
//			o.setOwner(u)
//			websiteRepo.save(o)
//			Page t = new Page()
//			t.setName("123")
//			t.setOwner(o)
//			pageRepo.save(t)
//			Notifications n = new Notifications();
//			n.setTitle("Title11");
//			n.setText("Text11");
//			n.setOwner(u);
//			n.setPage(t);
//			
//			
//		when: "Notification added to database"
//			alertRepo.save(n);
//		then: "Notification is found by its name"
//			assertFalse(alertRepo.findByTitle("Title11").isEmpty())
//			
//	}
	
	
	def "Notifications title null throws exception"() {
		given: "the context of the controller is setup with empty title"
			userRepo.deleteAll()
			pageRepo.deleteAll()
			websiteRepo.deleteAll()
			alertRepo.deleteAll()
			
			Website o = new Website()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("asd")
			userRepo.save(u)
			o.setOwner(u)
			websiteRepo.save(o)
			Page t = new Page()
			t.setName("123")
			t.setOwner(o)
			pageRepo.save(t)
			Notifications n = new Notifications();
			n.setTitle(null);
			n.setText("Text11");
			n.setOwner(u);
			n.setPage(t);
			
			
		when: "Notification added to database"
			alertRepo.save(n);
		then: "Throws Data Integrity Violation Exception"
			thrown(DataIntegrityViolationException);
	}
	//=======================================================
	//===========================Notifications====END========
	//=======================================================
	
	
	
	//=======================================================
	//===============How often page going to be checked======
	//=======================================================
	def "A user can set how often a page should be checked" () {
		given: "User, website and page created with page name"
			userRepo.deleteAll()
			pageRepo.deleteAll()
			websiteRepo.deleteAll()
			Website o = new Website()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("asd")
			userRepo.save(u)
			o.setOwner(u)
			websiteRepo.save(o)
			Page t = new Page()
			t.setName("123")
			t.setOwner(o)
			t.setFrequency("15");
			
			
		when: "Page is added to database"
			pageRepo.save(t)
		then: "Page is found by its frequency"
			assertFalse(pageRepo.findByFrequency("15").isEmpty())
	}
	//=======================================================
	//======How often page going to be checked======END======
	//=======================================================
	
	
	//=======================================================
	//===============Who gets alert about update=============
	//=======================================================
	def "A user can set who is alerted wehn page gets updated" () {
		given: "User, website and page created with page name"
			userRepo.deleteAll()
			pageRepo.deleteAll()
			websiteRepo.deleteAll()
			Website o = new Website()
			User u = new User();
			u.setLogin("asd")
			u.setPassword("asd")
			userRepo.save(u)
			o.setOwner(u)
			websiteRepo.save(o)
			Page t = new Page()
			t.setName("123")
			t.setOwner(o)
			t.setOwnerUrl("john@test.com");
			
			
		when: "Page is added to database"
			pageRepo.save(t)
		then: "Page is found by its Owner URL"
			assertFalse(pageRepo.findByOwnerUrl("john@test.com").isEmpty())
	}
	//=======================================================
	//===============Who gets alert about update=======END===
	//=======================================================
	

}
