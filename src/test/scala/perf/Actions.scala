package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Actions {

  //open
  val webtours = http("webtours")
    .get("/webtours/")
    .check(status is 200)

  val welcomePL = http("welcome.pl")
    .get("/welcome.pl")
    .queryParam("signOff", "true")
    .check(status is 200)

  val navPl = http("/cgi-bin/nav.pl?in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(status is 200)
    .check(regex("""name="userSession" value="(.+)"""").saveAs("userSession")) //сохраняем значение сессии пользователя в переменной saveAs

  //login
  val login = http("login")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "${userSession}")
    .formParam("username", "${login}")
    .formParam("password", "${password}")
    .formParam("login.x", "61")
    .formParam("login.y", "5")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)

  val login_navPL = http("login_navPL")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "home")
    .check(status is 200)

  val loginPL = http("loginPL")
    .get("/cgi-bin/login.pl")
    .queryParam("intro", "true")
    .check(status is 200)

  //flight

  val flightPL = http("flightPL")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "search")
    .check(status is 200)

  val flightNavPL = http("flightNavPL")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "flights")
    .check(status is 200)

  val reservations = http("reservations")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page","welcome")
    .check(status is 200)

  //buy ticket

  val buyTicket1 = http("buyTicket1")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount","0")
    .formParam("depart", "${departCity}")
    .formParam("departDate","02/05/2023")
    .formParam("arrive", "${arriveCity}")
    .formParam("returnDate","02/12/2023")
    .formParam("numPassengers","1")
    .formParam("seatPref","Window")
    .formParam("seatType","Business")
    .formParam("findFlights.x","41")
    .formParam("findFlights.y","6")
    .formParam(".cgifields","roundtrip")
    .formParam(".cgifields","seatType")
    .formParam(".cgifields","seatPref")
    .check(status is 200)
    .check(regex("""name="outboundFlight" value="([0-9;]+[\/]+[0-9]+[\/]+[0-9]+)" checked="checked"""").saveAs("outboundFlight"))

  val buyTicket2 = http("buyTicket2")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight","${outboundFlight}")
    .formParam("numPassengers","1")
    .formParam("advanceDiscount","0")
    .formParam("seatType","Business")
    .formParam("seatPref","Window")
    .formParam("reserveFlights.x","67")
    .formParam("reserveFlights.y","5")
    .check(status is 200)

  val buyTicket3 = http("buyTicket3")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "${outboundFlight}")
    .formParam("lastName", "1")
    .formParam("address1", "Mira")
    .formParam("address2", "Business")
    .formParam("seatPref", "Voronezh")
    .formParam("pass1", "Anna Semenova")
    .formParam("creditCard", "")
    .formParam("creditCard", "")
    .formParam("expDate", "")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Business")
    .formParam("seatPref", "Window")
    .formParam("outboundFlight", "121;343;02/11/2023")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "45")
    .formParam("buyFlights.y", "13")
    .formParam(".cgifields", "saveCC")
    .check(status is 200)

  //sign off

  val signoff = http("signoff")
    .get("/cgi-bin/welcome.pl")
    .queryParam("ignOff", "1")
    .check(status is 200)

}