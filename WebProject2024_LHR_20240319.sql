--------------------------------------------------------
--  파일이 생성됨 - 화요일-3월-19-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOARD
--------------------------------------------------------

  CREATE TABLE "WEBPROJECT"."BOARD" 
   (	"IDX" NUMBER, 
	"TITLE" VARCHAR2(100 BYTE), 
	"NAME" VARCHAR2(100 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"THUMB" NUMBER, 
	"VISITCOUNT" NUMBER, 
	"POSTDATE" DATE DEFAULT sysdate
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MEMBER
--------------------------------------------------------

  CREATE TABLE "WEBPROJECT"."MEMBER" 
   (	"ID" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(100 BYTE), 
	"PASS" VARCHAR2(100 BYTE), 
	"REGIDATE" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into WEBPROJECT.BOARD
SET DEFINE OFF;
Insert into WEBPROJECT.BOARD (IDX,TITLE,NAME,CONTENT,THUMB,VISITCOUNT,POSTDATE) values (1,'첫 번째 게시물','작성자1','첫 번째 게시물 내용입니다.',10,100,to_date('24/03/18','RR/MM/DD'));
Insert into WEBPROJECT.BOARD (IDX,TITLE,NAME,CONTENT,THUMB,VISITCOUNT,POSTDATE) values (2,'두 번째 게시물','작성자2','두 번째 게시물 내용입니다.',5,50,to_date('24/03/17','RR/MM/DD'));
REM INSERTING into WEBPROJECT.MEMBER
SET DEFINE OFF;
Insert into WEBPROJECT.MEMBER (ID,NAME,PASS,REGIDATE) values ('123','강경식','123',to_date('24/03/18','RR/MM/DD'));
--------------------------------------------------------
--  DDL for Index SYS_C008426
--------------------------------------------------------

  CREATE UNIQUE INDEX "WEBPROJECT"."SYS_C008426" ON "WEBPROJECT"."BOARD" ("IDX") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C008426
--------------------------------------------------------

  CREATE UNIQUE INDEX "WEBPROJECT"."SYS_C008426" ON "WEBPROJECT"."BOARD" ("IDX") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table BOARD
--------------------------------------------------------

  ALTER TABLE "WEBPROJECT"."BOARD" MODIFY ("POSTDATE" NOT NULL ENABLE);
  ALTER TABLE "WEBPROJECT"."BOARD" ADD PRIMARY KEY ("IDX")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "WEBPROJECT"."MEMBER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "WEBPROJECT"."MEMBER" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "WEBPROJECT"."MEMBER" MODIFY ("PASS" NOT NULL ENABLE);
  ALTER TABLE "WEBPROJECT"."MEMBER" MODIFY ("REGIDATE" NOT NULL ENABLE);
