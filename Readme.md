## Notification Engine


X is an investment platform. It provides STOCKS, MF, SGBs, IPO etc as financial instruments.

Any user placing an order in any of these instruments wants to be notified about the order journey

All the OMS service of these systems needs to send communication to the user.
These communications can have categories such as HIGH,LOW,MEDIUM based on how much delay is expected between the actual change of state and time when the user is notified.

There could be various mode of notifying the user

Design and implement a notification system which will serve the following purpose.

Accept requests from OMS/Login etc service to send the notification
Process and trigger notification to user based on priority


Example:
Let’s say user is investing in STOCKS. After order placement an order can go through following steps:

Step 1 - Order Starts with TO_DO state
Step 2 - ACCEPTED/REJECTED
Step 3 - EXECUTED/CANCELED




notifyUser(String message, String userId, String channel, String priority)
