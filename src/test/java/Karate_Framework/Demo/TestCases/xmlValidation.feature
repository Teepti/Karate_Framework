Feature: Response Validations in Xml
Scenario: when xpath exressions return xml chunks (or node lists)

# create a xml type object response
* def response = 
"""
		<teachers>
			<teacher department="science" id="309">
				<subject>math</subject>
				<subject>physics</subject>
			</teacher>
			<teacher department="arts" id="310">
				<subject>political education</subject>
				<subject>english</subject>
			</teacher>
		</teachers>
"""
# declare a xml type expected variable and initialize with expected xml type data
* def expected = <teacher department="science" id="309"><subject>math</subject><subject>physics</subject></teacher>
# declare another xml type variable and initialize with same data but with different way
* def teacher = //teacher[@department='science']
# Validate the both variable
* match teacher == expected
# Get reponse from xpath and store in a xml type variable
* def teacher = get response //teacher[@department='science']
# Validation of two xml variables
* match teacher == expected
# Validation can be done between xpath and variable contains xml data
* match //teacher[@department='science'] == expected




