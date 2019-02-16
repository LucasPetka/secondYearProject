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