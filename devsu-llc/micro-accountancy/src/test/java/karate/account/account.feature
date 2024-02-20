Feature: Testing a Account api

  Scenario: Testing valid GET endpoint
    Given url 'http://localhost:8881/api/cuentas'
    When method GET
    Then status 200

  Scenario: Testing an invalid GET endpoint - 404
    Given url 'http://localhost:8881/api/cuentas/7777'
    When method GET
    Then status 404

  Scenario: Testing the exact response of a GET endpoint
    Given url 'http://localhost:8881/api/cuentas/1'
    When method GET
    Then status 200
    And match $ == '<200 OK OK,AccountDto(numberAccount=1, identification=123456, typeAccount=CORRIENTE, amountInitial=250.0, state=ACTIVE),[]>'

  Scenario: Testing the exact response field value of a GET endpoint
    Given url 'http://localhost:8881/api/cuentas'
    When method GET
    Then status 200
    And match $ contains '[AccountDto(numberAccount=1, identification=123456, typeAccount=CORRIENTE, amountInitial=250.0, state=ACTIVE), AccountDto(numberAccount=2, identification=123456, typeAccount=AHORRO, amountInitial=550.0, state=ACTIVE)]'

  Scenario: Testing a POST endpoint with request body
    Given url 'http://localhost:8881/api/cuentas'
    And request { numberAccount: 1 }
    When method POST
    Then status 200
    And match $ contains 'AccountDto(numberAccount=1, identification=123456, typeAccount=CORRIENTE, amountInitial=250.0, state=ACTIVE)'

  Scenario: Testing a PUT endpoint with request body
    Given url 'http://localhost:8881/api/cuentas/1'
    And request { amountInitial: 250.0 }
    When method PUT
    Then status 200
    And match $ contains 'AccountDto(numberAccount=1, identification=123456, typeAccount=CORRIENTE, amountInitial=250.0, state=ACTIVE)'

  Scenario: Testing a DELETE endpoint
    Given url 'http://localhost:8881/api/cuentas/1'
    When method DELETE
    Then status 200
    And match $ contains 'Cuenta eliminada con éxito'