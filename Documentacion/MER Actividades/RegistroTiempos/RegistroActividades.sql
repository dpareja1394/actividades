/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     15/08/2016 7:19:40 p. m.                     */
/*==============================================================*/


drop table ACTIVIDAD;

drop table REGISTRO_ACTIVIDAD;

drop table USUARIO;

/*==============================================================*/
/* Table: ACTIVIDAD                                             */
/*==============================================================*/
create table ACTIVIDAD (
   ACTI_ID              SERIAL               not null,
   NOMBRE_ACTIVIDAD     CHARACTER VARYING(150)         not null,
   DESCRIPCION          CHARACTER VARYING(500)         not null,
   constraint PK_ACTIVIDAD primary key (ACTI_ID)
);

/*==============================================================*/
/* Table: REGISTRO_ACTIVIDAD                                    */
/*==============================================================*/
create table REGISTRO_ACTIVIDAD (
   ACTI_ID              BIGINT                 null,
   USUA_ID              BIGINT                 null,
   TIEMPO_MIN           BIGINT                 not null,
   REGI_ID              SERIAL               not null,
   TIEMPO_HORAS         TIME                 not null,
   FECHA_REGISTRO       DATE                 not null,
   FECHA_CREACION       DATE                 not null,
   constraint PK_REGISTRO_ACTIVIDAD primary key (REGI_ID)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   USUA_ID              SERIAL               not null,
   NOMBRE_COMPLETO      CHARACTER VARYING(200)         not null,
   EMAIL                CHARACTER VARYING(200)         not null,
   PASSWORD             CHARACTER VARYING(10)          not null,
   NUM_DOCUMENTO        CHARACTER VARYING(15)          not null,
   constraint PK_USUARIO primary key (USUA_ID)
);

alter table REGISTRO_ACTIVIDAD
   add constraint FK_REGISTRO_REFERENCE_ACTIVIDA foreign key (ACTI_ID)
      references ACTIVIDAD (ACTI_ID)
      on delete restrict on update restrict;

alter table REGISTRO_ACTIVIDAD
   add constraint FK_REGISTRO_REFERENCE_USUARIO foreign key (USUA_ID)
      references USUARIO (USUA_ID)
      on delete restrict on update restrict;

