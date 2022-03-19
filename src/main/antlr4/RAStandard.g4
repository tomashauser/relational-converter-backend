grammar RAStandard;

import ThetaCondition ;

IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_-]* ;

WS : [ \r\n\t]+ -> skip ;

BINARY_OPERATION : '\\cap' | '\\cup' | '\\times' | '\\setminus' | '\\div' ;

REGULAR_JOIN : '\\triangleleft' | '\\triangleright' | '\\leftouterjoin' | '\\rightouterjoin' | '\\fullouterjoin' ;

THETA_JOIN : '\\bowtie' | '\\ltimes' | '\\rtimes' ;

root : expr EOF
     ;

expr : '\\pi' '_{' columnList '}' '(' expr ')'                  # Projection
     | '\\sigma' thetaCondition '(' expr ')'                    # Selection
     | '\\rho' '_{' renameList '}' '(' expr ')'                 # Rename
     | expr BINARY_OPERATION expr                               # BinaryOperation
     | expr REGULAR_JOIN  expr                                  # JoinOperation
     | expr THETA_JOIN thetaCondition expr                      # JoinOperation
     | '(' expr ')'                                             # Parentheses
     | IDENTIFIER                                               # Relation
     ;

thetaCondition : ('_{' (formula)? '}')?
               ;

columnList : IDENTIFIER ( ',' IDENTIFIER )*
           ;

renameList : IDENTIFIER '/' IDENTIFIER ( ',' IDENTIFIER '/' IDENTIFIER )*
           ;

