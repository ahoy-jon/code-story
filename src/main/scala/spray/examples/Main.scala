package jon.codestory

import spray.can.server.SprayCanHttpServerApp
import akka.actor.Props


import java.io.File
import scala.concurrent.duration._
import org.parboiled.common.FileUtils
import akka.actor.{Props, Actor}
import spray.routing.{HttpService, RequestContext}
import spray.routing.directives.CachingDirectives
import spray.util.{SprayActorLogging, IOClosed}
import spray.httpx.encoding.Gzip
import spray.http._
import MediaTypes._
import CachingDirectives._

object Boot extends App with SprayCanHttpServerApp {

  // create and start our service actor
  val service = system.actorOf(Props[CodeStoryActor], "codeStoryService")

  // create a new HttpServer using our handler and tell it where to bind to
  newHttpServer(service) ! Bind(interface = "localhost", port = 8080)
}

class CodeStoryActor extends Actor with DemoService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(demoRoute)
}

trait DemoService extends HttpService {

  val demoRoute = {
    get {
      path("") {
        parameters('q) {
          case "Quelle est ton adresse email" => complete("jonathan.winandy+codestory@gmail.com")
          case s => complete(s)
        }
      }
    }
  }
}