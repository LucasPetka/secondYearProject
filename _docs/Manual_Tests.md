User story		Test						 				  Expected Result			         Actual Result
Add a website           Press Cancel button with name "" URL ""            				  Returns to Website List                       Pass
Add a website           Press Cancel button with name "test" URL "eRf"     				  Returns to Website List                       Pass
Add a website		Press Add button with name "" URL ""           					  Display an error on screen                    (FIXED) Fail: No error is shown and adds website with empty name and url
Add a website		Press Add button with name "site" URL"google.com"  				  Adds website and return to Website List       Pass
Add a website           Press Add button with name "site" URL "erwge"      				  Display an error on screen (Not a valid site) (FIXED) Fail: No error is shown and adds website with invalid URL


Delete a website        Press Delete button on website                     				  Website is deleted from list and database     Pass

Add a page name         Press Cancel button with name ""                   				  Returns to Page List                          Pass
Add a page name         Press Cancel button with name "test"               				  Returns to Page List                          Pass
Add a page name         Press Cancel button with name "A3294fm.2/"        				  Returns to Page List                          Pass
Add a page name         Press Cancel button with name "<script>alert(document.cookie);</script>"          Returns to Page List - No XSS                 Pass
Add a page name		Press Add button with name "<script>alert(document.cookie);</script>"             Adds page and returns to Page List            Pass
Add a page name         Press Add button with name ""                                                     Displays validation error on screen           Pass
Add a page name         Press Add button with name "/"                                                    Adds page and returns to Page List            Pass 
Add a page name         Press Add button with name ":::fwekrm324r3/////23f/"                              Displays validation error on screen           Pass

