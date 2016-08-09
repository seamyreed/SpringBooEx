package com.hjeong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class MainForAssembler {
    private static Assembler assembler = new Assembler();
    private static ApplicationContext appContext;

    public static void main(String[] args) throws IOException {

        appContext = new GenericXmlApplicationContext("classpath:applicationContext.xml");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요.:");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }

            if (command.startsWith("new")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change")) {
                processChangeCommand(command.split(" "));
            } else if (command.startsWith("list")) {
                processListCommand();
            }
        }
    }

    private static void processListCommand() {
        MemberListPrinter memberListPrinter =
                appContext.getBean("memberListPrinter", MemberListPrinter.class);
        memberListPrinter.printAll();
    }

    private static void processChangeCommand(String[] split) {
        if (split.length != 4) {
            printHelp();
            return;
        }

//        ChangePasswordService changeSvc = assembler.getChangePasswordService();
        ChangePasswordService changeSvc =
                appContext.getBean("changePasswordService", ChangePasswordService.class);
        try {
            changeSvc.changePassword(split[1], split[2], split[3]);
            System.out.println("비밀번호를 변경하였습니다.\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch (IdPasswordNotMatchingException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }

    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령어입니다 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }

    private static void processNewCommand(String[] split) {
        if (split.length != 5) {
            printHelp();
            return;
        }

//        MemberRegisterService regSvc = assembler.getMemberRegisterService();
        MemberRegisterService regSvc =
                appContext.getBean("memberRegisterService", MemberRegisterService.class);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(split[1]);
        registerRequest.setName(split[2]);
        registerRequest.setPassword(split[3]);
        registerRequest.setConfirmPassword(split[4]);

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            System.out.println("암호를 다시 확인해주세요.\n");
            return;
        }

        try {
            regSvc.regist(registerRequest);
            System.out.println("회원가입이 완료되었습니다. \n");
        } catch (AlreadyExistingMemberException e) {
            System.out.println("이미 존재하는 이메일입니다. \n");
        }
    }
}
