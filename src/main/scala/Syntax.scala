package plot

// An abstract implementation of abstract syntax.
// Scala makes it impossible to write arity-generic code without
// unacceptable type level gymnastics. It imposes the sort of boilerplate
// we're prefer not to encode into a language at a low level.
trait Syntax {
  type Name

  type Sort
  type Typ <: Sort   // type
  type Typs <: Sort  // list of types
  type Exp <: Sort   // expression
  type Exps <: Sort  // list of expressions

  // no sane way to constraint a variable length type list to Sort
  type Asts[In]
  type Ast[S <: Sort]
  type Var[S <: Sort] <: Ast[S]
  type Op[In, Out <: Sort] <: Ast[Out] with (In => Ast[Out])

  // working around absence of product types
  type Ast1[A1 <: Sort]                         = Ast[A1]
  type Ast2[A1 <: Sort, A2 <: Sort]             = ((Ast[A1], Ast[A2]))
  type Ast3[A1 <: Sort, A2 <: Sort, A3 <: Sort] = ((Ast[A1], Ast[A2], Ast[A3]))

  // encapsulating the conversion of higher-arity functions into uniformly unary input types
  def ast[R <: Sort](f: => Ast[R]): Op[Nothing, R]
  def ast[A1 <: Sort, R <: Sort](f: Ast[A1] => Ast[R]): Op[Ast[A1], R]
  def ast[A1 <: Sort, A2 <: Sort, R <: Sort](f: (Ast[A1], Ast[A2]) => Ast[R]): Op[Ast2[A1, A2], R]
  def ast[A1 <: Sort, A2 <: Sort, A3 <: Sort, R <: Sort](f: (Ast[A1], Ast[A2], Ast[A3]) => Ast[R]): Op[Ast3[A1, A2, A3], R]
}
