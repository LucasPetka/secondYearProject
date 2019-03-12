User story		Test						 				  			Expected Result			         Actual Result
Add a website           Press Cancel button with name "" URL ""            				  			Returns to Website List                       Pass
Add a website           Press Cancel button with name "test" URL "eRf"     				  			Returns to Website List                       Pass
Add a website		Press Add button with name "" URL ""           					  			Display an error on screen                    (FIXED) Fail: No error is shown and adds website with empty name and url
Add a website		Press Add button with name "site" URL"google.com"  				  			Adds website and return to Website List       Pass
Add a website           Press Add button with name "site" URL "erwge"      				  			Display an error on screen (Not a valid site) (FIXED) Fail: No error is shown and adds website with invalid URL


Delete a website        Press Delete button on website                     				  			Website is deleted from list and database     Pass


Add a page name         Press Cancel button with name ""                   				  			Returns to Page List                          Pass
Add a page name         Press Cancel button with name "test"               				  			Returns to Page List                          Pass
Add a page name         Press Cancel button with name "A3294fm.2/"        				  			Returns to Page List                          Pass
Add a page name         Press Cancel button with name "<script>alert(document.cookie);</script>"          			Returns to Page List - No XSS                 Pass
Add a page name		Press Add button with name "<script>alert(document.cookie);</script>"             			Adds page and returns to Page List            Pass
Add a page name         Press Add button with name ""                                                     			Displays validation error on screen           Pass
Add a page name         Press Add button with name "/"                                                    			Adds page and returns to Page List            Pass 
Add a page name         Press Add button with name ":::fwekrm324r3/////23f/"                              			Displays validation error on screen           Pass


Add a page url          Press Cancel button with url ""                    				  			Returns to Page List                          Pass
Add a page url          Press Cancel button with url "test"               				  			Returns to Page List                          Pass
Add a page url          Press Cancel button with url "A3294fm.2/"        				  			Returns to Page List                          Pass
Add a page url          Press Cancel button with url "<script>alert(document.cookie);</script>"           			Returns to Page List                          Pass
Add a page url		Press Add button with url  "<script>alert(document.cookie);</script>"             			Displays validation error on screen           (FIXED) Fail : (No XSS) but no validation for invalid URL
Add a page url          Press Add button with url  ""                                                     			Displays validation error on screen           Pass
Add a page url          Press Add button with url  "/"                                                    			Adds page and returns to Page List            Pass 
Add a page url          Press Add button with url  ":::fwekrm324r3/////23f/"                              			Displays validation error on screen           (FIXED) Fail: No validation for invalid URL (Need to check page status is 200)


Sign up                 Press Register button with email ""                                                                    Displays validation error on screen            Pass
Sign up                 Press Register button with email "testtefc3"                                                           Displays validation error on screen            Pass
Sign up                 Press Register button with email "test@test.com" and non matching passwords                            Displays validation error on screen            Pass
Sign up                 Press Register button with email "test@test.com" and matching passwords                                Adds user & Returns to Login page              Pass
Sign up                 Press Register button with email "test@test.com" after already signing                                 Displays validation error on screen            Pass
Sign up                 Press Register button with email "test2@test.com" and Password1* "test" Password2* ""                    Displays validation error on screen            Pass
Sign up                 Press Register button with email "test2@test.com" and Password1* "" Password2* "test"                    Displays validation error on screen            Pass
Sign up                 Press Register button with email "test2@test.com" and Password1* "" Password2* ""                        Displays validation error on screen            Pass
Sign up                 Press Register button with email "test2@test.com" and Password1* " " Password2* " "                      Displays validation error on screen    (FIXED) Fail : Password is accepted even though it is 1 digit long
Sign up                 Press Register button with email "test2@test.com" and PW "<script>alert(document.cookie);</script>"    Adds user & Returns to Login page - No XSS     Pass
Sign up                 Press Register button with email "<script>alert(document.cookie);</script>" and PW "123"               Displays validation error on screen - No XSS   Pass
Sign up                 Log out and press register button with taken email                                                     Displays validation error on screen            Pass
Sign up                 Press Register button with email "test@test.com" and matching passwords in incognito window            Adds user & Returns to Login page              Pass               
Sign up                 Press Register button with email "test@test.com" and non matching passwords in incognito window        Displays validation error on screen            Pass


Login			Press Login button with email "test" & password "test     		          			Displays validation error on screen	      Pass
Login			Press Login button with email "test@" & password "test    			  			Displays validation error on screen           Pass
Login			Press Login button with email "" password ""					 		 	Displays validation error on screen 	      Pass
Login			Press Login button with signed up email and password                              			Returns to Website List                       Pass
Login			Visit /addWebsite without logging in						  			Displays validation error on screen           Pass
Login			Visit /addPage without logging in						  			Displays validation error on screen           Pass
Login			Visit /PageList without logging in								 	Displays validation error on screen           Pass
Login			Visit /WebsiteList without logging in									Displays validation error on screen           Pass
Login                   Press Login button with signed up email and password in incognito browser         			Returns to Website List                       Pass
Login			Press Login button with email "<script>alert(document.cookie);</script>" & PW "a" 			Displays validation error on screen-No XSS    Pass
Login		        Press Login button with email "test@gmail.com" & PW "<script>alert(document.cookie);</script>"          Displays validation error on screen-No XSS    Pass

View page last updated  Change html code of tracked page 5 minutes after tracking the page                                      Updates time and adds to database and display Pass
View page last updated  Leave website unmodified for 24 hours                                                                   No changes to database or display             Pass
View page last updated  Leave website unmodified for 72 hours                                                                   No changes to database or display             Pass
View page last updated  Leave website unmodified for 1 week                                                                     No changes to database or display             Pass
View page last updated  Change html code of tracked page 1 week after tracking the page                                         Updates time and adds to database and display Pass
View page last updated  Change html code every hour for 5 hours                                                                 Each time new time is added to DB and display Pass
View page last updated  Change html code of tracked page 5 minutes after tracking page in a different country (Lithuania)       Updates time and adds to database and display Pass

Change password         Press update password with incorrect old password                                                       Error is shown - password not changed         Pass        
Change password         Press update password with correct old password and non-matching new passwords                          Error is shown - password not changed         Pass
Change password         Press update password with correct old password and new passwords "passwor"                             Error is shown - password not changed         Pass
Change password         Press update password with correct old password and new passwords "password"                            Error is shown - password not changed         Pass
Change password         Press update password with correct old password and new passwords "Password"                            Error is shown - Password not changed         Pass
Change password         Press update password with correct old password and new passwords "Password1"                           Password is changed and page refreshed        Pass
Change password         Press update password with correct old password and new passwords match criteria and 100000 digits long Error is shown - Password not changed         (FIXED) Fail (Password is changed)

Reset password		Press reset password with "test" as email address							Error is shown - Password not reset	      Pass
Reset password          Press reset password with "test@test..c.c.c" as email address						Error is shown - Password not reset           Pass
Reset password          Press reset password with "test@test.com" as email address (Non signed up user)				Error is shown - Password not reset           Pass
Reset password          Press reset password with signed up email address as email address					Password is reset & email sent		      Pass
Reset password		Press reset password with "<script>alert(document.cookie);</script>" as email address			Error is shown - No XSS			      Pass
Reset password          Press reset password with signed up user whilst it's logged in						Password is reset & email sent		      Pass
Reset password		Press reset password with signed up user before the email is verified					Password is reset & email needs validation    Pass

Select whos alerted	Add an email address "test"										Error is shown - Email address invalid	      Pass
Select whos alerted     Add an email address "test@test.c.c.c."                                                                 Error is shown - Email address invalid        Pass
Select whos alerted     Add an email address ""<script>alert(document.cookie);</script>"                                        Error is shown - Email address invalid-No XSS Pass
Select whos alerted     Add an email address "<script>alert(document.cookie);</script>@gmail.com"                               Error is shown - Email address invalid-No XSS Pass
Select whos alerted     Add a valid email e.g "test@test.com"									Email address is added to account	      Pass
Select whos alerted     User tries to add email address when none have been added to account					No email address show - cannot add one	      Pass
Select whos alerted	Email address is deleted after assigning it to a page 							Email is deleted from the page		      Pass
Select whos alerted	No email is assigned to page										Sign in email is chosen as default	      Pass









