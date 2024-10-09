import yfinance as yf
start_date = '2023-01-01'
end_date = '2024-08-01'

# Add multiple space separated tickers here
ticker = 'GOOGL MSFT TSLA'

data = yf.download(ticker, start_date, end_date)
print(data.tail())