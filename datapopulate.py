import sys
import requests
import json

def main():
	customerId = '574702718a710f8e12324ee0'
	apiKey = '482cef6d93c206eb7e5c9fefc8941ccb'

	url = 'http://api.reimaginebanking.com/customers/{}/accounts?key={}'.format(customerId,apiKey)
	payload = {
	  "type": "Savings",
	  "nickname": "Tryna save some dosh",
	  "rewards": 10000,
	  "balance": 10000,
	}
	# Create a Savings Account
	response = requests.post(
		url,
		data=json.dumps(payload),
		headers={'content-type':'application/json'},
		)

	if response.status_code == 201:
		print('account created')

if __name__ == "__main__":
	main()