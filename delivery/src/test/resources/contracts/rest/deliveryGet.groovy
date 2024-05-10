package contracts.rest

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url ('/deliveries/O12')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
                deliveryId: "O12",
                orderId: "O12",
                productId: "P12",
                productName: "TV",
                qty: 10
        )
        bodyMatchers {
            jsonPath('$.deliveryId', byRegex(nonEmpty()).asString())
            jsonPath('$.orderId', byRegex(nonEmpty()).asString())
            jsonPath('$.productId', byRegex(nonEmpty()).asString())
            jsonPath('$.productName', byRegex(nonEmpty()).asString())
            jsonPath('$.qty', byRegex(nonEmpty()).asInteger())
        }
        headers {
            contentType(applicationJson())
        }
    }
}
