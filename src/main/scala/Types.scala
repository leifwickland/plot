package plot

trait Types extends Any {
  type Expr
  type Type
  type Context // typing context - the types of bound names
}

trait Bindings extends Any {
  type Name
  type Context // lexical context - the name-independent identity of bound names
}
