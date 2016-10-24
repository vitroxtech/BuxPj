# BuxPj

2)why did not use intent services? 
because i think it was not necessary to implement an IntentService because mainly the socket just receive messages, 
is better to handle in main thread and not create separate thread for that, 
the memory allocated used is almost twice with a test that i did, also requires the use or broadcastreceiver which for this simple case because is only one activity interested on that data so  i think there is not sense but for a complex environment maybe is useful. 
why not a simple service? because run on main thread so in anycase i should use a thread or async or runnable to send the message to socket.

3)why I used RX for Rest and not for socket? 
because I should create a kind of RX-bus with personalized events just to be propagated (because they are already handled by Websocket) It can be sooooo much good do it , but it really takes time to do it in proper way. 
why did not used other bus like eventbus? because there is not sense to import a really heavy library just to manage two events that only one activity need to observe. 

4) why used an AsyncTask for send the message on socket? because the other methods provided by android like job can be putted on standby by android in this case the data need to be RealTime AsyncTask can do the job and also provides a simple implementation, 
for sure i could even use runnable with handler and other methods.

5)why used dependency injection? because it provides an easy and single creation of modules for different purposes.
