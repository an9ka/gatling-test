package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.Actions._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().mainScenario
}

class CommonScenario {

  //open
  val open = group("open"){
    exec(webtours)
    .exec(welcomePL)
      .exec(navPl)
  }
  //login

  val loginSite = group("loginSite") {
    exec(login)
      .exec(login_navPL)
      .exec(loginPL)
  }

 //flight
 val flights = group("flights") {
   exec(flightPL)
     .exec(flightNavPL)
     .exec(reservations)
 }

//buy ticket
val buyTicket = group("buyTicket") {
  exec(buyTicket1)
    .exec(buyTicket2)
    .exec(buyTicket3)
}

//sign off
val signOff = group("signOff") {
 exec(signoff)
}

  val mainScenario = scenario("mainScenario")
    .feed(Feeders.users)
    .feed(Feeders.departure)
    .feed(Feeders.arrival)
    .exec(open)
    .exec(loginSite)
    .exec(flights)
    .exec(buyTicket)
    .exec(signOff)
}
