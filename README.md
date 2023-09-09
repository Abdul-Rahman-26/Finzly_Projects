# Finzly_Projects

**FOREX**

**Forex Class**: This class acts as the main driver for the program. It contains the main method and a loop that displays options to the user and invokes relevant methods based on their choice.

**Trade Class**: This class represents a trade object with attributes like customer name, currency pair, amount, and whether to get the rate. It also contains a static constant RATE representing the conversion rate from USD to INR, as well as an ArrayList to store trades.

**BookTrade Class**: This class is responsible for handling trade-related operations such as adding new trades, displaying trade summaries, and printing trades. It interacts with the user to gather necessary information about the trade. The methods in this class provide a clear structure for performing these operations.

Updated to "*fxtrade*" which was used to create a Spring Boot-based REST API.

**Trade class**: Represents a trade with customer name, currency pair, amount, get rate, choice, and transferred amount.

**TradeService class**: Manages trade operations, including validation, rate calculations, and trade processing.

**BookTrade class**: A controller for handling trade-related HTTP requests, such as booking and listing trades. It interacts with the TradeService to perform trade operations.
