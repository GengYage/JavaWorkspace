package henu.hash;

import javax.xml.transform.Templates;
import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("del:删除雇员");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入name");
                    String name = scanner.next();
                    Emp emp = new Emp(name,id);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    int ID = scanner.nextInt();
                    hashTab.findEmpById(ID);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    int delId = scanner.nextInt();
                    hashTab.del(delId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }


}

//雇员信息
class Emp {
    public String name;
    public int id;
    public Emp next;
    public Emp(String name,int id) {
        super();
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

//hash表,管理多条链表
class HashTab {
    private EmpLinkList[] empLinkedListArray;
    private int size;
    public HashTab(int size) {
        //初始化链表
        this.size = size;
        empLinkedListArray = new EmpLinkList[this.size];
        for (int i = 0; i < this.size; i++) {
            empLinkedListArray[i] = new EmpLinkList();
        }
    }


    public void add(Emp emp) {
        //根据员工id 判断应该加入哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp放入指定链表
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    public void list() {
        if(size<=0) {
            System.out.println("表空");
        } else {
            for (int i = 0; i < this.size; i++) {
                empLinkedListArray[i].list(i+1);
            }
        }
    }
    public void findEmpById(int id) {
        int num = hashFun(id);
        Emp emp = empLinkedListArray[num].findEmpByID(id);
        if(emp!=null) {
            System.out.printf("该员工在第%d条链表,信息为:\n",num+1);
            System.out.println(emp.toString());
        } else {
            System.out.println("在hash表中不存在该雇员~~~");
        }
    }
    //删除
    public void del(int id) {
        int num = hashFun(id);
        empLinkedListArray[num].del(id);
    }
    //散列函数,取模法
    public int hashFun(int num) {
        return num%size;
    }
 }




//雇员链表
class EmpLinkList {
    private Emp head;

    //添加雇员
    public void add(Emp emp) {
        if(head==null) {
            head = emp;
            return;
        }

        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历
    public void list(int num) {
        if(head == null) {
            System.out.printf("第%d链表空~~\n",num);
            return;
        }
        Emp curEmp = head;
        System.out.printf("第%d条链表信息为{",num);
        while (true) {
            System.out.printf("=> id = %d,name = %s ",curEmp.id,curEmp.name);
            if(curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.print("}\n");
    }
    //查找
    public Emp findEmpByID(int id) {
        if(head==null) {
            System.out.println("链表空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if(curEmp.id==id) {
                break;
            }
            if(curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
    public void del(int id) {
        if(head==null) {
            System.out.println("表空,无法删除");
            return;
        }
        if(head.id == id) {
            head = head.next;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if(curEmp.next != null && curEmp.next.id==id) {
                curEmp = curEmp.next.next;
            }
            if (curEmp.next == null) {
                System.out.println("该表中无该雇员");
                break;
            }
            curEmp = curEmp.next;
        }
    }
}