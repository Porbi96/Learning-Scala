case class MapPoint (name: String, latitude: Double, longitude: Double) {

  def -(other: MapPoint): Double = {
    math.hypot(other.latitude - latitude, other.longitude - longitude)
  }

  def distanceTo(other: MapPoint): Double = this - other

  def move(dlat: Double, dlon: Double): MapPoint = {
    MapPoint("Nearby " + name, latitude + dlat, longitude + dlon)
  }

  override def toString: String = {
    val latStr = if (latitude >= 0) {
      latitude + "N"
    } else {
      -latitude + "S"
    }
    val lonStr = if (longitude >= 0) {
      longitude + "E"
    } else {
      -longitude + "W"
    }

    name + " " + latStr + " " + lonStr
  }
}
