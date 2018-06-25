<?xml version="1.0" ?>

<TestCase name="cars-baseline-rest-test" version="5">

<meta>
   <create version="9.1.0" buildNumber="9.1.0.261" author="admin" date="01/08/2016" host="admin" />
   <lastEdited version="9.1.0" buildNumber="9.1.0.261" author="admin" date="01/08/2016" host="admin" />
</meta>

<id>97686DA1B61511E5A26B406C8F055978</id>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjEuMCAoOS4xLjAuMjYxKSZub2Rlcz0xNjI4MDU0OTI=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

      <Companion type="com.itko.lisa.test.FailTestCaseCompanion" >
      </Companion>

    <Node name="cars" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA2B61511E5A26B406C8F055978" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="index.json" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>[{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:1,&quot;carTrim&quot;:&quot;Premium Plus&quot;,&quot;color&quot;:&quot;Silver&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:2,&quot;address&quot;:&quot;3800 Motor City Dr&quot;,&quot;city&quot;:&quot;Denver&quot;,&quot;name&quot;:&quot;Mountain Motors Inc&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-222-8766&quot;,&quot;website&quot;:&quot;www.mmdenver.com&quot;,&quot;zip&quot;:&quot;80202&quot;},&quot;engine&quot;:&quot;2.7 V6&quot;,&quot;image1&quot;:&quot;inventory1.jpg&quot;,&quot;image2&quot;:&quot;cars/interior1.jpg&quot;,&quot;makeid&quot;:2,&quot;milage&quot;:138560,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:10,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;A4-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2011,&quot;modelid&quot;:10,&quot;options&quot;:&quot;Leather, Navigation, Rear Air&quot;,&quot;owners&quot;:6,&quot;price&quot;:3995.0,&quot;stockNumber&quot;:&quot;NAD8989&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;2T1KR32E37C639014&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:2,&quot;carTrim&quot;:&quot;Technology&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:2,&quot;address&quot;:&quot;3800 Motor City Dr&quot;,&quot;city&quot;:&quot;Denver&quot;,&quot;name&quot;:&quot;Mountain Motors Inc&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-222-8766&quot;,&quot;website&quot;:&quot;www.mmdenver.com&quot;,&quot;zip&quot;:&quot;80202&quot;},&quot;engine&quot;:&quot;3.2 V6&quot;,&quot;image1&quot;:&quot;inventory2.jpg&quot;,&quot;image2&quot;:&quot;cars/interior2.jpg&quot;,&quot;makeid&quot;:1,&quot;milage&quot;:30650,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:2,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-AWD&quot;,&quot;subName&quot;:&quot;AWD&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2013,&quot;modelid&quot;:2,&quot;options&quot;:&quot;Leather, Navigation, Ski/BikeRack&quot;,&quot;owners&quot;:2,&quot;price&quot;:30995.0,&quot;stockNumber&quot;:&quot;RA2356/1&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;1ZVBP8AM1D5256900&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:3,&quot;carTrim&quot;:&quot;Premium&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:4,&quot;address&quot;:&quot;1673 Blake Rd&quot;,&quot;city&quot;:&quot;Aurora&quot;,&quot;name&quot;:&quot;Aurora Acura&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-955-0134&quot;,&quot;website&quot;:&quot;www.aurora_acura.com&quot;,&quot;zip&quot;:&quot;80203&quot;},&quot;engine&quot;:&quot;3.5 V6&quot;,&quot;image1&quot;:&quot;inventory3.jpg&quot;,&quot;image2&quot;:&quot;cars/interior3.jpg&quot;,&quot;makeid&quot;:1,&quot;milage&quot;:54650,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:2,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-AWD&quot;,&quot;subName&quot;:&quot;AWD&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2012,&quot;modelid&quot;:2,&quot;options&quot;:&quot;Leather, Navigation, Backup Camera&quot;,&quot;owners&quot;:2,&quot;price&quot;:30995.0,&quot;stockNumber&quot;:&quot;BN12560&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;3N1CB51D35l458771&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:4,&quot;carTrim&quot;:&quot;Sport&quot;,&quot;color&quot;:&quot;Silver&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:4,&quot;address&quot;:&quot;1673 Blake Rd&quot;,&quot;city&quot;:&quot;Aurora&quot;,&quot;name&quot;:&quot;Aurora Acura&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-955-0134&quot;,&quot;website&quot;:&quot;www.aurora_acura.com&quot;,&quot;zip&quot;:&quot;80203&quot;},&quot;engine&quot;:&quot;3.5 V6&quot;,&quot;image1&quot;:&quot;inventory4.jpg&quot;,&quot;image2&quot;:&quot;cars/interior4.jpg&quot;,&quot;makeid&quot;:1,&quot;milage&quot;:78400,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:2,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-AWD&quot;,&quot;subName&quot;:&quot;AWD&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2010,&quot;modelid&quot;:2,&quot;options&quot;:&quot;Ski Rack, Tow kit&quot;,&quot;owners&quot;:4,&quot;price&quot;:21405.0,&quot;stockNumber&quot;:&quot;BN13456&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;2T1KR32E37C639012&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:5,&quot;carTrim&quot;:&quot;Sport&quot;,&quot;color&quot;:&quot;Red&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:4,&quot;address&quot;:&quot;1673 Blake Rd&quot;,&quot;city&quot;:&quot;Aurora&quot;,&quot;name&quot;:&quot;Aurora Acura&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-955-0134&quot;,&quot;website&quot;:&quot;www.aurora_acura.com&quot;,&quot;zip&quot;:&quot;80203&quot;},&quot;engine&quot;:&quot;1.8T&quot;,&quot;image1&quot;:&quot;inventory5.jpg&quot;,&quot;image2&quot;:&quot;cars/interior5.jpg&quot;,&quot;makeid&quot;:2,&quot;milage&quot;:98145,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:11,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;S4-S&quot;,&quot;subName&quot;:&quot;Sport&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2008,&quot;modelid&quot;:11,&quot;options&quot;:&quot;Leather, Dual Air, 6 Speed&quot;,&quot;owners&quot;:4,&quot;price&quot;:7800.0,&quot;stockNumber&quot;:&quot;BN13467&quot;,&quot;transmission&quot;:&quot;Manual&quot;,&quot;vin&quot;:&quot;3N1CB51D35l458773&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:6,&quot;carTrim&quot;:&quot;xDrivePlus&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:5,&quot;address&quot;:&quot;126 Rte 76&quot;,&quot;city&quot;:&quot;Fort Collins&quot;,&quot;name&quot;:&quot;Hilton Honda&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-123-6789&quot;,&quot;website&quot;:&quot;www.hiltonhonda.com&quot;,&quot;zip&quot;:&quot;80503&quot;},&quot;engine&quot;:&quot;3.5L&quot;,&quot;image1&quot;:&quot;inventory6.jpg&quot;,&quot;image2&quot;:&quot;cars/interior6.jpg&quot;,&quot;makeid&quot;:3,&quot;milage&quot;:75350,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:27,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:3,&quot;name&quot;:&quot;BMW&quot;},&quot;modelYear&quot;:1990,&quot;name&quot;:&quot;7-Series-P&quot;,&quot;subName&quot;:&quot;Premium&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2010,&quot;modelid&quot;:27,&quot;options&quot;:&quot;Leather, Navigation, Rear Air&quot;,&quot;owners&quot;:1,&quot;price&quot;:34500.0,&quot;stockNumber&quot;:&quot;N2367J6&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;3N1CB51D35l458774&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:7,&quot;carTrim&quot;:&quot;Base&quot;,&quot;color&quot;:&quot;Blue&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:6,&quot;address&quot;:&quot;570 Lane St&quot;,&quot;city&quot;:&quot;Englewood&quot;,&quot;name&quot;:&quot;Terrys VW&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303- 560-3412&quot;,&quot;website&quot;:&quot;www.terry_vag.com&quot;,&quot;zip&quot;:&quot;80205&quot;},&quot;engine&quot;:&quot;2.0T&quot;,&quot;image1&quot;:&quot;inventory7.jpg&quot;,&quot;image2&quot;:&quot;cars/interior7.jpg&quot;,&quot;makeid&quot;:2,&quot;milage&quot;:104300,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:10,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;A4-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2008,&quot;modelid&quot;:10,&quot;options&quot;:&quot;Premium Audio Upgrade&quot;,&quot;owners&quot;:1,&quot;price&quot;:10750.0,&quot;stockNumber&quot;:&quot;ASN29001&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;2T1KR32E37C639015&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:8,&quot;carTrim&quot;:&quot;Premium&quot;,&quot;color&quot;:&quot;Silver&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:6,&quot;address&quot;:&quot;570 Lane St&quot;,&quot;city&quot;:&quot;Englewood&quot;,&quot;name&quot;:&quot;Terrys VW&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303- 560-3412&quot;,&quot;website&quot;:&quot;www.terry_vag.com&quot;,&quot;zip&quot;:&quot;80205&quot;},&quot;engine&quot;:&quot;2.0T&quot;,&quot;image1&quot;:&quot;inventory8.jpg&quot;,&quot;image2&quot;:&quot;cars/interior8.jpg&quot;,&quot;makeid&quot;:2,&quot;milage&quot;:62000,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:10,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;A4-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2012,&quot;modelid&quot;:10,&quot;options&quot;:&quot;Leather, Navigation, Bluetooth&quot;,&quot;owners&quot;:3,&quot;price&quot;:20400.0,&quot;stockNumber&quot;:&quot;ASN34501&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;1ZVBP8AM1D5256906&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:9,&quot;carTrim&quot;:&quot;Base&quot;,&quot;color&quot;:&quot;Silver&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:6,&quot;address&quot;:&quot;570 Lane St&quot;,&quot;city&quot;:&quot;Englewood&quot;,&quot;name&quot;:&quot;Terrys VW&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303- 560-3412&quot;,&quot;website&quot;:&quot;www.terry_vag.com&quot;,&quot;zip&quot;:&quot;80205&quot;},&quot;engine&quot;:&quot;3.0L&quot;,&quot;image1&quot;:&quot;inventory9.jpg&quot;,&quot;image2&quot;:&quot;cars/interior9.jpg&quot;,&quot;makeid&quot;:3,&quot;milage&quot;:58900,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:27,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:3,&quot;name&quot;:&quot;BMW&quot;},&quot;modelYear&quot;:1990,&quot;name&quot;:&quot;7-Series-P&quot;,&quot;subName&quot;:&quot;Premium&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2009,&quot;modelid&quot;:27,&quot;options&quot;:&quot;Leather, Navigation, Rear Air&quot;,&quot;owners&quot;:2,&quot;price&quot;:32295.0,&quot;stockNumber&quot;:&quot;ASN90845&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;1ZVBP8AM1D5256907&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:10,&quot;carTrim&quot;:&quot;Technology Plus&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:5,&quot;address&quot;:&quot;126 Rte 76&quot;,&quot;city&quot;:&quot;Fort Collins&quot;,&quot;name&quot;:&quot;Hilton Honda&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-123-6789&quot;,&quot;website&quot;:&quot;www.hiltonhonda.com&quot;,&quot;zip&quot;:&quot;80503&quot;},&quot;engine&quot;:&quot;3.5 V6&quot;,&quot;image1&quot;:&quot;inventory10.jpg&quot;,&quot;image2&quot;:&quot;cars/interior10.jpg&quot;,&quot;makeid&quot;:1,&quot;milage&quot;:54650,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:1,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2012,&quot;modelid&quot;:1,&quot;options&quot;:&quot;Leather, Navigation, Backup Camera&quot;,&quot;owners&quot;:2,&quot;price&quot;:30995.0,&quot;stockNumber&quot;:&quot;245980A&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;3N1CB51D35l458778&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:11,&quot;carTrim&quot;:&quot;Technology Plus&quot;,&quot;color&quot;:&quot;Green&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:5,&quot;address&quot;:&quot;126 Rte 76&quot;,&quot;city&quot;:&quot;Fort Collins&quot;,&quot;name&quot;:&quot;Hilton Honda&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-123-6789&quot;,&quot;website&quot;:&quot;www.hiltonhonda.com&quot;,&quot;zip&quot;:&quot;80503&quot;},&quot;engine&quot;:&quot;3.5 V8&quot;,&quot;image1&quot;:&quot;inventory11.jpg&quot;,&quot;image2&quot;:&quot;cars/interior11.jpg&quot;,&quot;makeid&quot;:1,&quot;milage&quot;:74850,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:1,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2011,&quot;modelid&quot;:1,&quot;options&quot;:&quot;Leather, Navigation&quot;,&quot;owners&quot;:1,&quot;price&quot;:20995.0,&quot;stockNumber&quot;:&quot;245180A&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;3N1CB41D32l458778&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:12,&quot;carTrim&quot;:&quot;Luxury&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:5,&quot;address&quot;:&quot;126 Rte 76&quot;,&quot;city&quot;:&quot;Fort Collins&quot;,&quot;name&quot;:&quot;Hilton Honda&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-123-6789&quot;,&quot;website&quot;:&quot;www.hiltonhonda.com&quot;,&quot;zip&quot;:&quot;80503&quot;},&quot;engine&quot;:&quot;3.0 V6&quot;,&quot;image1&quot;:&quot;inventory22.jpg&quot;,&quot;image2&quot;:&quot;cars/interior21.jpg&quot;,&quot;makeid&quot;:2,&quot;milage&quot;:24650,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:12,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;A4-C&quot;,&quot;subName&quot;:&quot;Convertible&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Convertible&quot;}},&quot;modelYear&quot;:2012,&quot;modelid&quot;:12,&quot;options&quot;:&quot;Leather&quot;,&quot;owners&quot;:2,&quot;price&quot;:11995.0,&quot;stockNumber&quot;:&quot;246987A&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;3H1CB51D35l453773&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:13,&quot;carTrim&quot;:&quot;Base&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:6,&quot;address&quot;:&quot;570 Lane St&quot;,&quot;city&quot;:&quot;Englewood&quot;,&quot;name&quot;:&quot;Terrys VW&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303- 560-3412&quot;,&quot;website&quot;:&quot;www.terry_vag.com&quot;,&quot;zip&quot;:&quot;80205&quot;},&quot;engine&quot;:&quot;3.0L&quot;,&quot;image1&quot;:&quot;inventory23.jpg&quot;,&quot;image2&quot;:&quot;cars/interior23.jpg&quot;,&quot;makeid&quot;:3,&quot;milage&quot;:58900,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:23,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:3,&quot;name&quot;:&quot;BMW&quot;},&quot;modelYear&quot;:1990,&quot;name&quot;:&quot;3-Series-S&quot;,&quot;subName&quot;:&quot;Sedan&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2011,&quot;modelid&quot;:23,&quot;options&quot;:&quot;Leather, Navigation&quot;,&quot;owners&quot;:2,&quot;price&quot;:12295.0,&quot;stockNumber&quot;:&quot;ASN90856&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;1XCBP8AM1D5256909&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:14,&quot;carTrim&quot;:&quot;Base&quot;,&quot;color&quot;:&quot;Black&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:6,&quot;address&quot;:&quot;570 Lane St&quot;,&quot;city&quot;:&quot;Englewood&quot;,&quot;name&quot;:&quot;Terrys VW&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303- 560-3412&quot;,&quot;website&quot;:&quot;www.terry_vag.com&quot;,&quot;zip&quot;:&quot;80205&quot;},&quot;engine&quot;:&quot;3.5L&quot;,&quot;image1&quot;:&quot;inventory24.jpg&quot;,&quot;image2&quot;:&quot;cars/interior24.jpg&quot;,&quot;makeid&quot;:3,&quot;milage&quot;:68900,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:26,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:3,&quot;name&quot;:&quot;BMW&quot;},&quot;modelYear&quot;:1990,&quot;name&quot;:&quot;5-Series-S&quot;,&quot;subName&quot;:&quot;Sedan&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2009,&quot;modelid&quot;:26,&quot;options&quot;:&quot;Leather, Navigation&quot;,&quot;owners&quot;:1,&quot;price&quot;:35295.0,&quot;stockNumber&quot;:&quot;ASN70851&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;7XCBP8AM1D5256910&quot;}]</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/inventory/cars</url>
<data-type>text</data-type>
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="index.json" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA3B61511E5A26B406C8F055978" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="index" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>[{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:3,&quot;name&quot;:&quot;BMW&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:4,&quot;name&quot;:&quot;Buick&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:5,&quot;name&quot;:&quot;Infiniti&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:6,&quot;name&quot;:&quot;Land Rover&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Lexus&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:8,&quot;name&quot;:&quot;Lincoln&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:9,&quot;name&quot;:&quot;Volvo&quot;}]</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/inventory/carMake/index.json</url>
<data-type>text</data-type>
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="index" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA4B61511E5A26B406C8F055978" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="2T1KR32E37C639014" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>[{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:1,&quot;address&quot;:&quot;1070 Corzon St&quot;,&quot;city&quot;:&quot;Longmont&quot;,&quot;name&quot;:&quot;XYZ Motors Inc&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-555-1234&quot;,&quot;website&quot;:&quot;www.xyzmotorsco.com&quot;,&quot;zip&quot;:&quot;80501&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:2,&quot;address&quot;:&quot;3800 Motor City Dr&quot;,&quot;city&quot;:&quot;Denver&quot;,&quot;name&quot;:&quot;Mountain Motors Inc&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-222-8766&quot;,&quot;website&quot;:&quot;www.mmdenver.com&quot;,&quot;zip&quot;:&quot;80202&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:3,&quot;address&quot;:&quot;235 Spine Rd&quot;,&quot;city&quot;:&quot;Boulder&quot;,&quot;name&quot;:&quot;Blanes GMC&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-456-9090&quot;,&quot;website&quot;:&quot;www.blanemotors.com&quot;,&quot;zip&quot;:&quot;80202&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:4,&quot;address&quot;:&quot;1673 Blake Rd&quot;,&quot;city&quot;:&quot;Aurora&quot;,&quot;name&quot;:&quot;Aurora Acura&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-955-0134&quot;,&quot;website&quot;:&quot;www.aurora_acura.com&quot;,&quot;zip&quot;:&quot;80203&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:5,&quot;address&quot;:&quot;126 Rte 76&quot;,&quot;city&quot;:&quot;Fort Collins&quot;,&quot;name&quot;:&quot;Hilton Honda&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-123-6789&quot;,&quot;website&quot;:&quot;www.hiltonhonda.com&quot;,&quot;zip&quot;:&quot;80503&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:6,&quot;address&quot;:&quot;570 Lane St&quot;,&quot;city&quot;:&quot;Englewood&quot;,&quot;name&quot;:&quot;Terrys VW&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303- 560-3412&quot;,&quot;website&quot;:&quot;www.terry_vag.com&quot;,&quot;zip&quot;:&quot;80205&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:7,&quot;address&quot;:&quot;3556 Motor City Dr&quot;,&quot;city&quot;:&quot;Denver&quot;,&quot;name&quot;:&quot;Blanes Toyota&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-100-2310&quot;,&quot;website&quot;:&quot;www.blanemotors.com&quot;,&quot;zip&quot;:&quot;80201&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:8,&quot;address&quot;:&quot;4100 Motor City Dr&quot;,&quot;city&quot;:&quot;Denver&quot;,&quot;name&quot;:&quot;Denver Subaru&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-670-1402&quot;,&quot;website&quot;:&quot;www.milehighsubaru.com&quot;,&quot;zip&quot;:&quot;80201&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:9,&quot;address&quot;:&quot;100 Lisle Ave&quot;,&quot;city&quot;:&quot;Aurora&quot;,&quot;name&quot;:&quot;Capital Ford&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-555-1200&quot;,&quot;website&quot;:&quot;www.capitalford.com&quot;,&quot;zip&quot;:&quot;80503&quot;},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:10,&quot;address&quot;:&quot;1050 University Dr&quot;,&quot;city&quot;:&quot;Boulder&quot;,&quot;name&quot;:&quot;Boulder Chrysler Group&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-204-8720&quot;,&quot;website&quot;:&quot;www.jeep_boulder.com&quot;,&quot;zip&quot;:&quot;80503&quot;}]</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/inventory/carDealer/index</url>
<data-type>text</data-type>
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="2T1KR32E37C639014" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA5B61511E5A26B406C8F055978" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="models.json" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>{&quot;class&quot;:&quot;com.ca.lisa.demo.CarInventory&quot;,&quot;id&quot;:1,&quot;carTrim&quot;:&quot;Premium Plus&quot;,&quot;color&quot;:&quot;Silver&quot;,&quot;dealer&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarDealer&quot;,&quot;id&quot;:2,&quot;address&quot;:&quot;3800 Motor City Dr&quot;,&quot;city&quot;:&quot;Denver&quot;,&quot;name&quot;:&quot;Mountain Motors Inc&quot;,&quot;state&quot;:&quot;CO&quot;,&quot;telephone&quot;:&quot;303-222-8766&quot;,&quot;website&quot;:&quot;www.mmdenver.com&quot;,&quot;zip&quot;:&quot;80202&quot;},&quot;engine&quot;:&quot;2.7 V6&quot;,&quot;image1&quot;:&quot;inventory1.jpg&quot;,&quot;image2&quot;:&quot;cars/interior1.jpg&quot;,&quot;makeid&quot;:2,&quot;milage&quot;:138560,&quot;model&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:10,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:2,&quot;name&quot;:&quot;Audi&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;A4-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},&quot;modelYear&quot;:2011,&quot;modelid&quot;:10,&quot;options&quot;:&quot;Leather, Navigation, Rear Air&quot;,&quot;owners&quot;:6,&quot;price&quot;:3995.0,&quot;stockNumber&quot;:&quot;NAD8989&quot;,&quot;transmission&quot;:&quot;Auto&quot;,&quot;vin&quot;:&quot;2T1KR32E37C639014&quot;}</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/inventory/carInventory/2T1KR32E37C639014</url>
<data-type>text</data-type>
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="models.json" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA6B61511E5A26B406C8F055978" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="10.139.4.217:7001/loan" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>[{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:1,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:2,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-AWD&quot;,&quot;subName&quot;:&quot;AWD&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:3,&quot;fuelType&quot;:&quot;Hybrid&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;RLX-H&quot;,&quot;subName&quot;:&quot;Hybrid&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:4,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;TL-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:5,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;TL-AWD&quot;,&quot;subName&quot;:&quot;SH-AWD&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:6,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:1996,&quot;name&quot;:&quot;TL-S&quot;,&quot;subName&quot;:&quot;Sport&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:7,&quot;name&quot;:&quot;Sedan&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:7,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2000,&quot;name&quot;:&quot;MDX-B&quot;,&quot;subName&quot;:&quot;Base&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:9,&quot;name&quot;:&quot;SUV&quot;}},{&quot;class&quot;:&quot;com.ca.lisa.demo.CarModel&quot;,&quot;id&quot;:8,&quot;fuelType&quot;:&quot;Gas&quot;,&quot;make&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarMake&quot;,&quot;id&quot;:1,&quot;name&quot;:&quot;Acura&quot;},&quot;modelYear&quot;:2013,&quot;name&quot;:&quot;MDX-AWD&quot;,&quot;subName&quot;:&quot;SH-AWD&quot;,&quot;type&quot;:{&quot;class&quot;:&quot;com.ca.lisa.demo.CarType&quot;,&quot;id&quot;:9,&quot;name&quot;:&quot;SUV&quot;}}]</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/inventory/carMake/1/models.json</url>
<data-type>text</data-type>
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="10.139.4.217:7001/loan" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA7B61511E5A26B406C8F055978" 
          think="500-1s" 
          useFilters="true" 
          quiet="false" 
          next="lisa.simpson" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Assert Response Equals" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Actual response does not match expected response.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$</jsonPath>
        <expectedValue>{&quot;status&quot;:&quot;accepted&quot;}</expectedValue>
        <ignoreArrayOrder>true</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/loan</url>
<content>{&quot;username&quot;:&quot;lisa.simpson&quot;,&quot;firstName&quot;:&quot;Lisa&quot;,&quot;lastName&quot;:&quot;Simpson&quot;,&quot;address1&quot;:&quot;5465 Legacy Dr&quot;,&quot;city&quot;:&quot;Plano&quot;,&quot;state&quot;:&quot;TX&quot;,&quot;zip&quot;:&quot;75024&quot;,&quot;country&quot;:&quot;US&quot;,&quot;dob&quot;:&quot;01/01/1970&quot;,&quot;ssn&quot;:&quot;111-22-3333&quot;,&quot;employer&quot;:&quot;CA Technologies&quot;,&quot;phoneNumber&quot;:&quot;123-456-7891&quot;,&quot;durationOfJob&quot;:&quot;5 Years&quot;,&quot;income&quot;:&quot;95808&quot;,&quot;loanTerm&quot;:3,&quot;loanAmount&quot;:44224.0,&quot;validateAddress&quot;:false}</content>
<content-type>application/json; charset=UTF-8</content-type>
<data-type>text</data-type>
      <header field="Content-Type" value="application/json; charset=UTF-8" />
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>POST</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="lisa.simpson" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="97686DA8B61511E5A26B406C8F055978" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Assert Response Code Equals" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Assert Response Code Equals checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="true" name="Ensure Result Contains" type="com.ca.lisa.apptest.json.AssertJSONContains">
<log></log>
<then>fail</then>
        <jsonPath>$</jsonPath>
        <AnyOf>false</AnyOf>
        <expectedValue>&quot;durationOfJob&quot;:&quot;5 Years&quot;, ### &quot;firstName&quot;:&quot;Lisa&quot;, ### &quot;lastName&quot;:&quot;Simpson&quot;, ### &quot;username&quot;:&quot;lisa.simpson&quot;, ### &quot;ssn&quot;:&quot;111-22-3333&quot;, ### &quot;loanTermInMonths&quot;:60,</expectedValue>
        <ignoreArrayOrder>false</ignoreArrayOrder>
</CheckResult>

<url>http://{{WSSERVER}}:{{WSPORT}}/loan/lisa.simpson</url>
<data-type>text</data-type>
      <header field="User-Agent" value="Java/1.8.0_60" />
      <header field="Host" value="10.139.4.217:7001" />
      <header field="Accept" value="text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="abort" log="The test was aborted"
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="97686DABB61511E5A26B406C8F055978" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="97686DAAB61511E5A26B406C8F055978" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="97686DA9B61511E5A26B406C8F055978" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


</TestCase>
