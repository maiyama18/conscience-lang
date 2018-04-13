grammar Conscience;

file : block ;

block : stat+ ;

stat : assignStat
     | printStat
     ;
assignStat : ID '=' expr NEWLINE ;
printStat : 'print' expr NEWLINE ;

expr : expr op=('*'|'/') expr # MulDivExpr
     | expr op=('+'|'-') expr # AddSubExpr
     | ID                             # IdExpr
     | INT                            # IntExpr
     ;


ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE : '\r'? '\n' ;
WS : [ \t] -> skip ;
