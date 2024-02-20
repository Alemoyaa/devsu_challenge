Feature: Testing a Customers api

  Scenario: Testing valid GET endpoint
    Given url 'http://localhost:8881/api/clientes'
    When method GET
    Then status 200

  Scenario: Testing an invalid GET endpoint - 404
    Given url 'http://localhost:8881/api/clientes/7777'
    When method GET
    Then status 404

  Scenario: Testing the exact response of a GET endpoint
    Given url 'http://localhost:8881/api/clientes/1'
    When method GET
    Then status 200
    And match $ == '<200 OK OK,CustomerDto(customerId=1, password=123456, state=ACTIVE),[]>'

  Scenario: Testing the exact response field value of a GET endpoint
    Given url 'http://localhost:8881/api/clientes'
    When method GET
    Then status 200
    And match $ contains 'CustomerDto(customerId=1, password=123456, state=ACTIVE), CustomerDto(customerId=2, password=123456, state=ACTIVE)'

  Scenario: Testing a POST endpoint with request body
    Given url 'http://localhost:8881/api/clientes'
    And request { customerId: 1, password: "123456" }
    When method POST
    Then status 200
    And match $ contains 'CustomerDto(customerId=1, password=123456, state=ACTIVE)'

  Scenario: Testing a PUT endpoint with request body
    Given url 'http://localhost:8881/api/clientes/1'
    And request { password: "777741" }
    When method PUT
    Then status 200
    And match $ contains 'CustomerDto(customerId=1, password=777741, state=ACTIVE)'

  Scenario: Testing a DELETE endpoint
    Given url 'http://localhost:8881/api/clientes/1'
    When method DELETE
    Then status 200
    And match $ contains 'Cliente eliminado con éxito'