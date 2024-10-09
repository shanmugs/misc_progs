# Importing the yfinance package
import yfinance as yf

# Set the start and end date
start_date = '2023-01-01'
end_date = '2024-08-01'

# Set the ticker
ticker = 'GOOGL'

# Get the data
data = yf.download(ticker, start_date, end_date)

# Print last 5 rows
print(data.tail())