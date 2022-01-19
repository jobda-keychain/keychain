package com.jobda.keychain.entity.log;

/**
 * 로그 저장할 때 어떤 메소드를 실행했는지 확인하는 enum
 * url이나 http method를 직접 저장하면 변경됐을 때 동일한 기능으로 인식을 못하기 때문에 타입으로 지정하여 저장하기로 함
 *
 * ADD_ACCOUNT(계정 추가), UPDATE_ACCOUNT(계정 수정), DELETE_ACCOUNT(계정 삭제), DETAILS_ACCOUNT(계정 상세보기),
 * ADD_ENVIRONMENT(환경 추가), UPDATE_ENVIRONMENT(환경 수정), DELETE_ENVIRONMENT(환경 삭제)
 *
 * @author: syxxn
 **/
public enum MethodType {
    ADD_ACCOUNT, UPDATE_ACCOUNT, DELETE_ACCOUNT, DETAILS_ACCOUNT,
    ADD_ENVIRONMENT, UPDATE_ENVIRONMENT, DELETE_ENVIRONMENT
}
