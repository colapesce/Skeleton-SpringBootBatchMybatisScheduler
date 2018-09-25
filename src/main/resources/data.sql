/*
DELETE FROM BATCH_STEP_EXECUTION_CONTEXT ;
DELETE FROM BATCH_STEP_EXECUTION ;
DELETE FROM BATCH_JOB_EXECUTION_CONTEXT ;
DELETE FROM BATCH_JOB_EXECUTION_PARAMS ;
DELETE FROM BATCH_JOB_EXECUTION ;
DELETE FROM BATCH_JOB_INSTANCE ;

DELETE FROM BATCH_STEP_EXECUTION_SEQ ;
DELETE FROM BATCH_JOB_EXECUTION_SEQ ;
DELETE FROM BATCH_JOB_SEQ ;

INSERT INTO BATCH_STEP_EXECUTION_SEQ values(0);
INSERT INTO BATCH_JOB_EXECUTION_SEQ values(0);
INSERT INTO BATCH_JOB_SEQ values(0);
*/

insert into JOB_CONFIG values (1, 'lottoJob', null, to_date('20180918', 'yyyymmdd'));
insert into STEP_CONFIG values (1, 1, 'step1', 1, '{ "reader": { "className":"CSVFileReaderBuilder", "info": { "mapperClass":"EstrazioneFieldSetMapper", "resource":"fileRepos/inputs/storico.txt", "tokenNames":["dataestrazione","ruota","colonna1","colonna2","colonna3","colonna4","colonna5"], "delimiter":"\t" } }, "processor": { "className":"EstrazioneItemProcessorBuilder", "info":{} }, "writer": { "className":"DBWriterBuilder", "info":{"restartable":"true", "mapperStatement":"EstrazioneDao.insertOne"} }, "chunk":"10000"}', 'step', to_date('20180918', 'yyyymmdd'));
insert into STEP_CONFIG values (2, 1, 'step2', 2, '{ "reader": { "className":"DBReaderBuilder", "info": { "mapperClass":"RuotaFieldSetMapper", "mapperStatement":"RuotaDao.findFromEstrazioni"} }, "processor": { "className":"RuoteItemProcessorBuilder", "info":{} }, "writer": { "className":"DBWriterBuilder", "info":{"restartable":"true", "mapperStatement":"RuotaDao.insertOne"} }, "chunk":"10000" }', 'step', to_date('20180918', 'yyyymmdd'));

insert into JOB_CONFIG values (2, 'taskJob', null, to_date('20180918', 'yyyymmdd'));
insert into STEP_CONFIG values (3, 2, 'tasklet', 1, '{ "tasklet": { "className":"TestTasklet", "info": {"restartable":"true"} } }', 'tasklet', to_date('20180918', 'yyyymmdd'));

commit;