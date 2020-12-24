//package com.reactive.demo.common;
//
//import io.prometheus.client.Counter;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
///**
// * @author hy
// * @Date 2020/12/23 11:37
// */
//
//@Aspect
//@Component
//@Slf4j
//public class PrometheusMetricAspect {
//
//    private static final Counter requestTotal = Counter.build().name("counter_total")
//            .labelNames("interface","caller","code","status").help("request counter").register();
//
//    private static final Counter requestSuccessTime = Counter.build().name("gauge_success_time")
//            .labelNames("interface","caller","code","status").help("request counter").register();
//
//    private static final Counter requestSuccessCnt = Counter.build().name("gauge_success_cnt")
//            .labelNames("interface","caller","code","status").help("request counter").register();
//
//    private static final Counter requestError = Counter.build().name("counter_error")
//            .labelNames("interface","caller","code","status").help("request counter").register();
//
//    private static final Counter histogram = Counter.build().name("requests_latency_seconds")
//            .labelNames("interface","caller").help("request counter").register();
//
//    @Pointcut("@annotation(com.reactive.demo.common.PrometheusMetrics)")
//    public void pointCutMethod(){
//    }
//
//    @Pointcut("execution(reactor.core.publisher.Mono *(..))")
//    public void returnMono(){
//
//    }
//
//    @Pointcut("execution(reactor.core.publisher.Flux *(..))")
//    public void returnFlux(){
//
//    }
//
//
//    @Around(value = "pointCutMethod() && returnMono() && @annotation(annotation)")
//    public Object measureMono(ProceedingJoinPoint joinPoint,PrometheusMetrics annotation) throws Throwable {
//        return ((Mono<?>) joinPoint.proceed())
//                .doOnSuccess(x -> record())
//                .doOnError(x -> record());
//    }
//
//    @Around(value = "pointCutMethod() && returnFlux() && @annotation(annotation)")
//    public Object measureFlux(ProceedingJoinPoint joinPoint,PrometheusMetrics annotation) throws Throwable {
//        Object object  = joinPoint.proceed();
//        if (object instanceof Flux){
//
//        }
//
//        long start = System.currentTimeMillis();
//        return ((Flux<?>) joinPoint.proceed())
//                .doOnComplete(() -> record())
//                .doOnError(x -> record());
//
//    }
//
//
//
//
//    public void recordSuccess(ProceedingJoinPoint joinPoint,PrometheusMetrics annotation,long start){
//
//    }
//
//    private void record(ProceedingJoinPoint joinPoint,PrometheusMetrics annotation, long start, String status){
//
//    }
//
//}
