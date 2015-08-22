import play.api.GlobalSettings
import twitter._

object Global extends GlobalSettings {

	override def onStart(application: play.api.Application) {

	}

	override def onStop(application: play.api.Application) {
		TwitterStream.system.shutdown()
	}
}
