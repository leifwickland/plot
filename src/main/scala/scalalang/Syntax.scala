package plot
package scalalang

// A slightly less abstract implementation of scala's abstract syntax
abstract class ScalaSyntax extends Syntax {
  import scala.reflect.runtime.{ universe => u }

  // Imaginary implicits convert back and forth between scala's
  // Trees and our AST nodes.
  implicit def toExp(t: u.Tree): Ast[Exp]
  implicit def fromExp(e: Ast[Exp]): u.Tree
  implicit def fromExps(e: Ast[Exps]): List[u.Tree]
  implicit def toType(t: u.Tree): Ast[Typ]
  implicit def fromTyp(e: Ast[Typ]): u.Tree
  implicit def fromTyps(e: Ast[Typs]): List[u.TypTree]

  // String promotion for our convenience.
  implicit def stringToExp(s: String): Ast[Exp]
  implicit def stringToTyp(s: String): Ast[Typ]
  implicit def stringsToTyps(s: List[String]): Ast[Typs]
  implicit def stringsToExps(s: List[String]): Ast[Exps]

  def typs(names: String*): Ast[Typs] = stringsToTyps(names.toList)
  def exps(names: String*): Ast[Exps] = stringsToExps(names.toList)

  // Just a sampling
  def DotType   = ast[Exp, Typ](u.SingletonTypeTree(_))    // x => x.type
  def Typed     = ast[Exp, Typ, Exp](u.Typed(_, _))        // (5, Int) => (5: Int)
  def If        = ast[Exp, Exp, Exp, Exp](u.If(_, _, _))   // (true, 5, 10) => ( if (true) 5 else 10 )
  def TypeApply = ast[Typ, Typs, Typ](u.TypeApply(_, _))   // (List, Int) => List[Int]
  def Apply     = ast[Exp, Exps, Exp](u.Apply(_, _))       // (f, x) => f(x)

  // For instance
  def tapply = TypeApply("List", typs("Int"))
}
