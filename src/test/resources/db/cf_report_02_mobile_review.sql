#----------------------------------------#
#---------------Part2:电审---------------#
#----------------------------------------#

##电审新增待审笔数	电审新增待审金额
drop table bigdata_ecf_bi.report_mobile_review_tmp1;
create table bigdata_ecf_bi.report_mobile_review_tmp1 as
select t.*
from(

select hour(b.begintime) as hours
      ,count(*) as mobile_review_add_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_add_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'
group by hour(b.begintime)

union all

select '当天累计' as hours
      ,count(*) as mobile_review_add_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_add_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'

union all

select '历史累计' as hours
      ,count(*) as mobile_review_add_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_add_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'

)t

;

##电审完成笔数	电审完成金额
drop table bigdata_ecf_bi.report_mobile_review_tmp2;
create table bigdata_ecf_bi.report_mobile_review_tmp2 as
select t.*
from(

select hour(b.begintime) as hours
      ,count(*) as mobile_review_finish_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_finish_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'
  AND b.endtime is not null
group by hour(b.begintime)

union all

select '当天累计' as hours
      ,count(*) as mobile_review_finish_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_finish_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'
  AND b.endtime is not null

union all

select '历史累计' as hours
      ,count(*) as mobile_review_finish_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_finish_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'
  AND b.endtime is not null

)t
;

##电审拒绝笔数	电审拒绝金额
drop table bigdata_ecf_bi.report_mobile_review_tmp3;
create table bigdata_ecf_bi.report_mobile_review_tmp3 as
select t.*
from(

select hour(b.begintime) as hours
      ,count(*) as mobile_review_refuse_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_refuse_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
join(select OBJECTNO, begintime from ecf_webapp.FLOW_TASK where OBJECTTYPE='jbo.app.BUSINESS_APPLY' and FLOWNO='CreditFlow' and phasename='已拒绝')c on b.OBJECTNO=c.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow'
  AND b.PHASENO='0015'
  AND b.endtime=c.begintime
group by hour(b.begintime)

union all

select '当天累计' as hours
      ,count(*) as mobile_review_refuse_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_refuse_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
join(select OBJECTNO, begintime from ecf_webapp.FLOW_TASK where OBJECTTYPE='jbo.app.BUSINESS_APPLY' and FLOWNO='CreditFlow' and phasename='已拒绝')c on b.OBJECTNO=c.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow'
  AND b.PHASENO='0015'
  AND b.endtime=c.begintime

union all

select '历史累计' as hours
      ,count(*) as mobile_review_refuse_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_refuse_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
join(select OBJECTNO, begintime from ecf_webapp.FLOW_TASK where OBJECTTYPE='jbo.app.BUSINESS_APPLY' and FLOWNO='CreditFlow' and phasename='已拒绝')c on b.OBJECTNO=c.OBJECTNO
where b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow'
  AND b.PHASENO='0015'
  AND b.endtime=c.begintime

)t
;

##电审待审笔数
drop table bigdata_ecf_bi.report_mobile_review_tmp4;
create table bigdata_ecf_bi.report_mobile_review_tmp4 as
select '当天累计' as hours
      ,count(*) as mobile_review_wait_cnt
      ,sum(a.BUSINESSSUM) as mobile_review_wait_amt
from ecf_webapp.BUSINESS_APPLY a
join ecf_webapp.FLOW_TASK b on a.SERIALNO=b.OBJECTNO
where date(b.begintime)='2017-04-20'
  AND b.OBJECTTYPE='jbo.app.BUSINESS_APPLY' 
  AND b.FLOWNO='CreditFlow' 
  AND b.PHASENO='0015'
  AND b.endtime is null
;

##合并电审报表
drop table bigdata_ecf_bi.report_mobile_review;
create table bigdata_ecf_bi.report_mobile_review as
select t.hours_desc
      ,coalesce(t1.mobile_review_add_cnt    ,0) as mobile_review_add_cnt   
      ,coalesce(t1.mobile_review_add_amt    ,0) as mobile_review_add_amt   
      ,coalesce(t2.mobile_review_finish_cnt ,0) as mobile_review_finish_cnt
      ,coalesce(t2.mobile_review_finish_amt ,0) as mobile_review_finish_amt
      ,coalesce(t3.mobile_review_refuse_cnt ,0) as mobile_review_refuse_cnt
      ,coalesce(t3.mobile_review_refuse_amt ,0) as mobile_review_refuse_amt
      ,coalesce(t4.mobile_review_wait_cnt   ,0) as mobile_review_wait_cnt  
      #,coalesce(t4.mobile_review_wait_amt   ,0) as mobile_review_wait_amt   
from bigdata_ecf_bi.hours t
left outer join bigdata_ecf_bi.report_mobile_review_tmp1 t1 on t.hours=t1.hours
left outer join bigdata_ecf_bi.report_mobile_review_tmp2 t2 on t.hours=t2.hours
left outer join bigdata_ecf_bi.report_mobile_review_tmp3 t3 on t.hours=t3.hours
left outer join bigdata_ecf_bi.report_mobile_review_tmp4 t4 on t.hours=t4.hours
order by t.hours_rank desc
;
