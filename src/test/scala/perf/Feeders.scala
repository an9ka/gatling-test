package perf

import io.gatling.core.Predef._

object Feeders {
  val users = csv("users.csv").circular
  val departure = csv("departure.csv").shuffle.random
  val arrival = csv("arrival.csv").shuffle.random
}
