grammar ThetaCondition;

NUMBER : ('0' .. '9') + ('.' ('0' .. '9') +)? ;

STRING_VALUE : '\'' [a-zA-Z0-9_-]+ '\'' ;

IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_-]* ;

WS : [ \r\n\t]+ -> skip ; // Skip white space

COMPARISON_OP : '>' | '<' | '=' | '\\lneq' | '\\leq' | '\\geq' ;

BINARY_CONNECTIVE : '\\land' | '\\lor' ;

UNARY_CONNECTIVE : '\\lnot' ;

formula : columnSpec COMPARISON_OP columnSpec         # Predicate
        | columnSpec COMPARISON_OP term               # Predicate
        | term       COMPARISON_OP columnSpec         # Predicate
        | '(' formula ')'                             # FormulaParentheses
        | UNARY_CONNECTIVE formula                    # NotOperation
        | formula BINARY_CONNECTIVE formula           # BinaryLogicalOperation
        | formula BINARY_CONNECTIVE formula           # BinaryLogicalOperation
        ;

columnSpec : IDENTIFIER                               # ColumnSpecification
           ;

term : STRING_VALUE                                   # StringTerm
     | NUMBER                                         # NumberTerm
     ;