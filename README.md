# OpalClient
Customized Java client code for invoking Opal Toolkit services

Step 1: Add this project as a dependency to your main project
Step 2: In your main project override (create in root package) provided sample OpalClient.properties with a similar file pointing to targeted Opal services
Step 3: From your main project's code, just call OpalJobInvoker.invokeBlocking() or OpalJobInvoker.invokeNonBlocking()
Step 4: If you called the non-blocking method, use getJobStatus to see how it goes
