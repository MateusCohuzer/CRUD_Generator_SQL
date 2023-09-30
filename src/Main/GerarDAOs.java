package Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ConverteDatas;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Cohuzer
 */
public class GerarDAOs {

    public GerarDAOs(String nomeDaClasse, List<String> atributo, String caminho) {
        //DAO ESPECIFICO
        Ferramentas f = new Ferramentas();
        ConverteDatas converteDatas = new ConverteDatas();
        List<String> codigo = new ArrayList();
        st st = new st();
        String nomeDaClasseMin = st.plMinus(nomeDaClasse);
        
        String[] aux = atributo.get(0).split(";");
        String[] aux1 = atributo.get(1).split(";");
        
        codigo.add("package DAOs;\n\n\n");

        codigo.add("import Entidades." + nomeDaClasse + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n");

        codigo.add("\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + "* @author Mateus Batichotti Silva | " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + "*/\n");

        codigo.add("public class DAO" + nomeDaClasse + " extends DAOGenerico<" + nomeDaClasse + ">{\n");
        codigo.add("private List<" + nomeDaClasse + "> lista = new ArrayList<>();\n"
                + "\n"
                + "    public DAO" + nomeDaClasse + "() {\n"
                + "        super(" + nomeDaClasse + ".class);\n"
                + "    }\n");

        codigo.add("public int autoId" + nomeDaClasse + "() {\n"
                + "        Integer a = (Integer) em.createQuery(\"SELECT MAX(e." + aux[1] + ") FROM " + nomeDaClasse + " e \").getSingleResult(); \n"
                + "        if (a != null) {\n"
                + "            return a + 1;\n"
                + "        } else {\n"
                + "            return 1;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeDaClasse + "> listByNome(String nome) {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeDaClasse + " e WHERE e." + aux[1] + " LIKE :nome\")\n"
                + "         .setParameter(\"nome\", \"%\" + nome + \"%\")\n"
                + "         .getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeDaClasse + "> listById(int id) {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeDaClasse + " e WHERE e." + aux1[1] + "= :id\").setParameter(\"id\", id).getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeDaClasse + "> listInOrderNome() {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeDaClasse + " e ORDER BY e." + aux[1] + "\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeDaClasse + "> listInOrderId() {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeDaClasse + " e ORDER BY e." + aux1[1] + "\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<String> listInOrderNomeStrings(String qualOrdem) {\n"
                + "        List<" + nomeDaClasse + "> lf;\n"
                + "        if (qualOrdem.equals(\"id\")) {\n"
                + "            lf = listInOrderId();\n"
                + "        } else {\n"
                + "            lf = listInOrderNome();\n"
                + "        }\n"
                + "\n"
                + "        List<String> ls = new ArrayList<>();\n"
                + "        for (int i = 0; i < lf.size(); i++) {\n"
                + "            ls.add(lf.get(i).get" + aux1[1] + "()+ \"-\" + lf.get(i).get" + aux[1] + "());\n"
                + "        }\n"
                + "        return ls;\n"
                + "    }\n"
                + "\n"
                + "    public static void main(String[] args) {\n"
                + "        DAO" + nomeDaClasse + " dao" + nomeDaClasse + " = new DAO" + nomeDaClasse + "();\n"
                + "        List<" + nomeDaClasse + "> lista" + nomeDaClasse + " = dao" + nomeDaClasse + ".list();\n"
                + "        for (" + nomeDaClasse + " " + nomeDaClasseMin + " : lista" + nomeDaClasse + ") {\n"
                + "            System.out.println(" + nomeDaClasseMin + ".get" + aux[1] + "()+ \"-\" + " + nomeDaClasseMin + ".get" + aux1[1] + "());\n"
                + "        }\n"
                + "    }\n"
                + "}\n"
                + "");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/DAOs/DAO" + nomeDaClasse + ".java", codigo);

        List<String> codigo2 = new ArrayList();

        codigo2.add("package DAOs;");

        codigo2.add("import java.util.List;\n"
                + "import javax.persistence.EntityManager;\n"
                + "import javax.persistence.Persistence;");

        codigo2.add("\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + "* @author Mateus Batichotti Silva | " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + "*/\n");

        codigo2.add("public class DAOGenerico<T> {\n"
                + "\n"
                + "    public static EntityManager em = Persistence.createEntityManagerFactory(\"UP\").createEntityManager();\n"
                + "    private Class clazz;\n"
                + "\n"
                + "    public DAOGenerico(Class clazz) {\n"
                + "        this.clazz = clazz;\n"
                + "    }\n"
                + "\n"
                + "    public void inserir(T e) {\n"
                + "        em.getTransaction().begin();\n"
                + "        em.persist(e);\n"
                + "        em.getTransaction().commit();\n"
                + "    }\n"
                + "\n"
                + "    public void atualizar(T e) {\n"
                + "        em.getTransaction().begin();\n"
                + "        em.merge(e);\n"
                + "        em.getTransaction().commit();\n"
                + "    }\n"
                + "\n"
                + "    public void remover(T e) {\n"
                + "        em.getTransaction().begin();\n"
                + "        em.remove(e);\n"
                + "        em.getTransaction().commit();\n"
                + "    }\n"
                + "\n"
                + "    public T obter(Long id) {\n"
                + "        Object ent = em.find(clazz, id);\n"
                + "        if (ent == null) {\n"
                + "            return (T) ent;\n"
                + "        } else {\n"
                + "            em.refresh(ent);\n"
                + "            return (T) ent;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public T obter(Integer id) {\n"
                + "        Object ent = em.find(clazz, id);\n"
                + "        if (ent == null) {\n"
                + "            return (T) ent;\n"
                + "        } else {\n"
                + "            em.refresh(ent);\n"
                + "            return (T) ent;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public T obter(String id) {\n"
                + "        Object ent = em.find(clazz, id);\n"
                + "        if (ent == null) {\n"
                + "            return (T) ent;\n"
                + "        } else {\n"
                + "            em.refresh(ent);\n"
                + "            return (T) ent;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public List<T> list() {\n"
                + "        return em.createQuery(\"SELECT e FROM \" + clazz.getSimpleName() + \" e\").getResultList();\n"
                + "    }\n"
                + "}");
        
        manipulaArquivo.salvarArquivo(caminho + "/src/DAOs/DAOGenerico.java", codigo2);
    }

}
