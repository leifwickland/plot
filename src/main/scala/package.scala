package object plot {
  // Allows function application when the parameter list has been tupled.
  // If you're lucky.
  implicit class FixFunctionOne[-In, +Out](f: In => Out) {
    def apply(p: In): Out                                                                = f(p)
    def apply[A1, A2](p1: A1, p2: A2)(implicit ev: (A1, A2) <:< In): Out                 = apply((p1, p2))
    def apply[A1, A2, A3](p1: A1, p2: A2, p3: A3)(implicit ev: (A1, A2, A3) <:< In): Out = apply((p1, p2, p3))
  }

  implicit final class AnyExtensions[A](val lhs: A) extends AnyVal {
    def toRef: AnyRef = lhs.asInstanceOf[AnyRef]
  }

  def path(s: String) = java.nio.file.Paths get s
}
