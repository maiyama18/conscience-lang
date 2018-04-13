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
     | INT                        # IntExpr
     | '(' expr ')'               # ParenExpr
     ;


ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
WS : [ \t\r\n] -> skip ;
