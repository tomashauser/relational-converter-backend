grammar RASimplified;

import ThetaCondition ;

IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_-]* ;

WS : [ \r\n\t]+ -> skip ; // Skip white space

BINARY_OPERATION_SYMBOL : '\\cap' | '\\cup' | '\\times' | '\\setminus' | '\\div' ;

JOIN_OPERATION_SYMBOL : '*' | '<*' | '*>' | '*L' | '*_L' | '*_{L}' | '*R' | '*_R' | '*_{R}' | '*F' | '*_F' | '*_{F}' | '\\triangleleft' | '\\triangleright' ;

root : expr EOF
     ;

expr : expr '[' columnList ']'                                  # Projection
     | expr '(' thetaCondition ')'                              # Selection
     | expr '\\langle' renameList '\\rangle'                    # Rename
     | expr BINARY_OPERATION_SYMBOL expr                        # BinaryOperation
     | expr JOIN_OPERATION_SYMBOL expr                          # JoinOperation
     | expr '[' thetaCondition ']' expr                         # JoinOperation
     | expr '\\langle' thetaCondition ']' expr                  # JoinOperation
     | expr '[' thetaCondition '\\rangle' expr                  # JoinOperation
     | '(' expr ')'                                             # Parentheses
     | IDENTIFIER                                               # Relation
     ;

thetaCondition : (formula)?
               ;

columnList : IDENTIFIER ( ',' IDENTIFIER )*
           ;

renameList : IDENTIFIER '\\rightarrow' IDENTIFIER ( ',' IDENTIFIER '\\rightarrow' IDENTIFIER )*
           ;