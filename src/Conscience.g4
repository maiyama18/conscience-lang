grammar Conscience;

file : block ;

block : stat+ ;

stat : assignStat
     | printStat
     ;
assignStat : ID '=' expr ';' ;
printStat : 'print' expr ';' ;

expr : expr op=('*'|'/'|'%') expr # MulDivModExpr
     | expr op=('+'|'-') expr     # AddSubExpr
     | ID                         # IdExpr
     | INT                        # IntExpr
     | '(' expr ')'               # ParenExpr
     ;


ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
WS : [ \t\r\n] -> skip ;
