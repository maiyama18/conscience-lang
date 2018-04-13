grammar Conscience;

file : block ;

block : stat+ ;

stat : assignStat
     | mutateStat
     | printStat
     | ifStat
     | whileStat
     ;
assignStat : 'let' ID '=' expr ';' ;
mutateStat : ID '=' expr ';' ;
printStat : 'print' expr ';' ;
ifStat : 'if' expr '{' block '}' ;
whileStat : 'while' expr '{' block '}' ;

expr : expr op=('*'|'/'|'%') expr # MulDivModExpr
     | expr op=('+'|'-') expr     # AddSubExpr
     | ID                         # IdExpr
     | INTEGER                    # IntegerExpr
     | STRING                     # StringExpr
     | BOOLEAN                    # BooleanExpr
     | '(' expr ')'               # ParenExpr
     ;


BOOLEAN : ('true'|'false') ;
INTEGER : [0-9]+ ;
STRING : '"' .*? '"' ;
ID : [a-zA-Z]+ ;
WS : [ \t\r\n] -> skip ;
