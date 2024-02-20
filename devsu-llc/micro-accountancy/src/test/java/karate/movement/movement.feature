Feature: Testing a Movement api

  Scenario: Testing valid GET endpoint
    Given url 'http://localhost:8881/api/movimientos'
    When method GET
    Then status 200

  Scenario: Testing an invalid GET endpoint - 404
    Given url 'http://localhost:8881/api/movimientos/7777'
    When method GET
    Then status 404

  Scenario: Testing the exact response of a GET endpoint
    Given url 'http://localhost:8881/api/movimientos/1'
    When method GET
    Then status 200
    And match $ contains '<200 OK OK,MovementDto(id=1, date=Mon Feb 19 00:00:00 ART 2024, typeMovement=INGRESO, value=177.1, amount=755.0, numberAccount=2),[]>'

  Scenario: Testing the exact response field value of a GET endpoint
    Given url 'http://localhost:8881/api/movimientos'
    When method GET
    Then status 200
    And match $ contains '[MovementDto(id=1, date=Mon Feb 19 00:00:00 ART 2024, typeMovement=INGRESO, value=177.1, amount=755.0, numberAccount=2), MovementDto(id=2, date=Mon Feb 19 00:00:00 ART 2024, typeMovement=EGRESO, value=177.1, amount=755.0, numberAccount=2)]'

  Scenario: Testing a POST endpoint with request body
    Given url 'http://localhost:8881/api/movimientos'
    And request { customerId: 1, password: "123456" }
    When method POST
    Then status 200
    And match $ contains 'MovementDto(id=1, date=Mon Feb 19 00:00:00 ART 2024, typeMovement=INGRESO, value=177.1, amount=755.0, numberAccount=2)'

  Scenario: Testing a DELETE endpoint
    Given url 'http://localhost:8881/api/movimientos/1'
    When method DELETE
    Then status 200
    And match $ contains 'Movimiento eliminado con éxito'