package plot

// Lots of agnosticism about what any of these things actually are
// This is only to provide a very broad structure under which we
// can compose language elements.
//
// Identified assumptions made here include:
// - Any input of interest can be modeled as a tree
// - ...

trait Compiler {
  type Input
  type Output
  type Tree

  type Syntax <: plot.Syntax
  type Types <: plot.Types
  type Bindings <: plot.Bindings

  type Sort = Syntax#Sort
  type Ast  = Syntax#Ast[_]
  type Type = Types#Type
  type Expr = Types#Expr

  type Abt <: Tree // abstract binding tree - ast augmented with scopes

  def parse(in: Input): Ast
  def bind(tree: Ast, ctx: Bindings#Context): Abt
  def typed(tree: Abt, ctx: Types#Context): Type
}
