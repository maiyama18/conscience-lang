grammar Conscience;

file : block ;

block : stat+ ;

stat : assignStat
     | mutateStat
     | printStat
     | ifStat
     | whileStat
     | fnDefStat
     | exprStat
     ;
assignStat : 'let' ID '=' expr ';' ;
mutateStat : ID '=' expr ';' ;
printStat : 'print' expr ';' ;
ifStat : 'if' expr '{' block '}' ;
whileStat : 'while' expr '{' block '}' ;
fnDefStat : 'fn' ID '=' '(' ids? ')' '=>' '{' block '}' ;
exprStat : expr ';' ;

ids : ID (',' ID)* ;
params : expr (',' expr)* ;
expr : expr op=('*'|'/'|'%') expr             # MulDivModExpr
     | expr op=('+'|'-') expr                 # AddSubExpr
     | expr op=('>'|'<'|'<='|'>='|'==') expr  # CompareExpr
     | ID '(' params? ')'                     # FnCallExpr
     | ID                                     # IdExpr
     | INTEGER                                # IntegerExpr
     | STRING                                 # StringExpr
     | BOOLEAN                                # BooleanExpr
     | '(' expr ')'                           # ParenExpr
     ;


BOOLEAN : ('true'|'false') ;
INTEGER : [0-9]+ ;
STRING : '"' .*? '"' ;
ID : [a-zA-Z]+ ;
WS : [ \t\r\n] -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
