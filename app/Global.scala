import play.api.GlobalSettings
import twitter._

object Global extends GlobalSettings {

	override def onStart(application: play.api.Application) {
		TweetStream.streamActor ! TweetStreamActor.TweetStreamStart
	}

	override def onStop(application: play.api.Application) {
		TweetStream.system.shutdown()
	}
}