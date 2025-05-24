@RestController
@RequestMapping("/contexto")
public class ContextoController {

    @Autowired
    private ContextoRepository contextoRepository;

    @GetMapping
    public List<Contexto> listar() {
        return contextoRepository.findAll();
    }

    @PostMapping
    public Contexto salvar(@RequestBody Contexto contexto) {
        return contextoRepository.save(contexto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        contextoRepository.deleteById(id);
    }
}
