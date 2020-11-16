object StringDecorators {
  trait htmlH2 {
    override def toString: String = "<H2>" + super.toString + "</H2>"
  }

  trait htmlI {
    override def toString: String = "<I>" + super.toString + "</I>"
  }

  trait Capitalisation {
    override def toString: String = super.toString.capitalize
  }

  trait PageStretch {
    val llen = 60
    val spaces = super.toString.count(_ == ' ')

    override def toString: String = {
      super.toString.replace(" ", (" ") * ((llen - super.toString.length - spaces) / spaces))
    }
  }
}
