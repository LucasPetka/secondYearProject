User story		Test						   Expected Result			         Actual Result
Add a website           Press Cancel button with name "" URL ""            Returns to Website List                       Pass
Add a website           Press Cancel button with name "test" URL "eRf"     Returns to Website List                       Pass
Add a website		Press Add button with name "" URL ""               Display an error on screen                    Fail: No error is shown and adds website with empty name and url
Add a website		Press Add button with name "site" URL"google.com"  Adds website and return to Website List       Pass
Add a website           Press Add button with name "site" URL "erwge"      Display an error on screen (Not a valid site) Fail: No error is shown and adds website with invalid URL


Delete a website        Press Delete button on website                     Website is deleted from list and database     Pass

